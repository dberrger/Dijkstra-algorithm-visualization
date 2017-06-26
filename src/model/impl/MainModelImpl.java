package model.impl;

import model.MainModel;
import structures.Node;

import java.io.File;

public class MainModelImpl implements MainModel {
    //DRAW MODEL OBJECT
    private DrawModelImpl drawModel;
    //DIJKSTRA ALGORITHM OBJECT
    private DijkstraAlgorithmModelImpl dijkstraAlgorithmModel;
    //GRAPH MODEL OBJECT
    private GraphModelImpl graphModelImpl;
    public MainModelImpl(){

        //DRAW MODEL AND DIJKSTRA ALGORITHM OBJECT INITIALIZATION
        drawModel = new DrawModelImpl();
        dijkstraAlgorithmModel= new DijkstraAlgorithmModelImpl();
        graphModelImpl = new GraphModelImpl();
    }
    //NEXT TURN METHOD
    @Override
    public void onNextTurn() {
        graphModelImpl.nextTurn();
        ObservableModelImpl.getInstance().setGraph(drawModel.drawGraph(graphModelImpl.getCurrentTurnGraph()));
    }
    //PREV TURN METHOD
    @Override
    public void onPrevTurn() {
        graphModelImpl.prevTurn();
        ObservableModelImpl.getInstance().setGraph(drawModel.drawGraph(graphModelImpl.getCurrentTurnGraph()));
    }
    //ADD NODE METHOD
    @Override
    public void addNode() {
        //CLEAR TURNS
        graphModelImpl.clearTurns();

        ObservableModelImpl.getInstance().setAlgorithmState(false);
        //SET MESSAGE
        ObservableModelImpl.getInstance().setMessage("NEW NODE ADDED");
        //ADD NODE TO INITIAL GRAPH OF SINGLETON
        graphModelImpl.addNodeToInitialGraph();
        //GET XY COORDINATE OF INITIAL GRAPH NODES
        drawModel.getNodesXY(graphModelImpl.getInitialGraph());
        //DRAW INITIAL GRAPH
        ObservableModelImpl.getInstance().setGraph(drawModel.drawGraph(graphModelImpl.getInitialGraph()));
    }

    @Override
    public void addEdge(int first, int second, int weight) {
        //CLEAR TURNS
        graphModelImpl.clearTurns();
        //ADD EDGE TO INITIAL GRAPH
        ObservableModelImpl.getInstance().setMessage("EDGE IS ADDED");
        graphModelImpl.addEdgeToInitialGraph(first,second,weight);
        ObservableModelImpl.getInstance().setAlgorithmState(false);
        //DRAW INITIAL GRAPH
        ObservableModelImpl.getInstance().setGraph( drawModel.drawGraph(graphModelImpl.getInitialGraph()));
    }

    @Override
    public void deleteEdge(int first, int second) {
        graphModelImpl.clearTurns();
        //DELETE EDGE IN INITIAL GRAPH
        ObservableModelImpl.getInstance().setMessage("EDGE IS DELETED");
        graphModelImpl.deleteEdgeInInitialGraph(first, second);
        ObservableModelImpl.getInstance().setAlgorithmState(false);
        //DRAW INITIAL GRAPH
        ObservableModelImpl.getInstance().setGraph(drawModel.drawGraph(graphModelImpl.getInitialGraph()));
    }
    @Override
    public void runAlgorithm(int startPoint) {
        try{
            if (graphModelImpl.getInitialGraph().nodes.get(startPoint)!=null) {
                //SET START POINT
                dijkstraAlgorithmModel.setStartPoint(startPoint);
                //RUN ALGORITHM ON INITIAL GRAPH
                graphModelImpl.setTurns(dijkstraAlgorithmModel.DijkstraAlgorithm(graphModelImpl.getInitialGraph()));
                for (Node n : graphModelImpl.getTurns().get(graphModelImpl.getTurns().size()-1).nodes){
                    System.out.println(n.prev.size());
                }
                ObservableModelImpl.getInstance().
                        setShortestPaths(dijkstraAlgorithmModel.getShortestPaths(
                                graphModelImpl.getTurns().get(graphModelImpl.getTurns().size() - 1)
                        ));
                ObservableModelImpl.getInstance().setAlgorithmState(true);
                ObservableModelImpl.getInstance().setGraph(drawModel.drawGraph(graphModelImpl.getInitialGraph()));
                ObservableModelImpl.getInstance().setMessage("ALGORITHM FINISHED WORK");
            }
        }
        catch (IndexOutOfBoundsException e){
            ObservableModelImpl.getInstance().setMessage("NO POINT WITH INDEX " + startPoint);
        }
    }

    @Override
    public void toLastStep() {
        //SET TURN TO LAST STEP
        graphModelImpl.setTurn(graphModelImpl.getTurns().size()-1);
        //DRAW GRAPH
        ObservableModelImpl.getInstance().setGraph(drawModel.drawGraph(graphModelImpl.getCurrentTurnGraph()));
        ObservableModelImpl.getInstance().setMessage("LAST STEP");
    }

    @Override
    public void toFirstStep() {
        //SET TURN TO FIRST STEP
        graphModelImpl.setTurn(0);
        //DRAW GRAPH
        ObservableModelImpl.getInstance().setGraph(drawModel.drawGraph(graphModelImpl.getCurrentTurnGraph()));
        ObservableModelImpl.getInstance().setMessage("FIRST STEP");
    }

    @Override
    public void clearGraph() {
        //CLEAR TURNS CLEAR INITIAL GRAPH
        graphModelImpl.clearTurns();
        graphModelImpl.clearInitialGraph();
        ObservableModelImpl.getInstance().setAlgorithmState(false);
        ObservableModelImpl.getInstance().setGraph(
                drawModel.drawGraph(graphModelImpl.getInitialGraph()));
        ObservableModelImpl.getInstance().setMessage("GRAPH CLEARED");
    }

    @Override
    public void readGraphFromFile(File file) {
        GraphActionsModelImpl g = new GraphActionsModelImpl();
        graphModelImpl.clearTurns();
        graphModelImpl.clearInitialGraph();
        ObservableModelImpl.getInstance().setAlgorithmState(false);
        graphModelImpl.setInitialGraph(g.readGraphFromFile(file));
        ObservableModelImpl.getInstance().setGraph(drawModel.drawGraph(graphModelImpl.getInitialGraph()));
    }
}
