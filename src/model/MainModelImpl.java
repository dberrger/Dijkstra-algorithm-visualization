package model;

import structures.Node;

import java.util.ArrayList;

public class MainModelImpl implements MainModel{
    private DrawModelImpl drawModel;
    private DijkstraAlgorithmModel dijkstraAlgorithmModel;
    public MainModelImpl(){
        drawModel = new DrawModelImpl();
        dijkstraAlgorithmModel= new DijkstraAlgorithmModel();
    }
    @Override
    public void onNextTurn() {
        ObservableModelImpl.getInstance().nextTurn();
        drawModel.drawGraph();
        ObservableModelImpl.getInstance().setMessage("NEXT TURN");
    }

    @Override
    public void onPrevTurn() {
        ObservableModelImpl.getInstance().prevTurn();
        drawModel.drawGraph();
        ObservableModelImpl.getInstance().setMessage("PREV TURN");
    }

    @Override
    public void deleteNode() {

    }

    @Override
    public void addNode() {
        ObservableModelImpl.getInstance().addNodeToInitialGraph();
        drawModel.getNodesXY();
        drawModel.drawInitialGraph();
    }

    @Override
    public void addEdge(int first, int second, int weight) {
        ObservableModelImpl.getInstance().addEdgeToInitialGraph(first,second,weight);
        drawModel.drawInitialGraph();
    }

    @Override
    public void deleteEdge() {

    }

    @Override
    public void runAlgorithm() {
        dijkstraAlgorithmModel.setStartPoint(0);
        dijkstraAlgorithmModel.DijkstraAlgorithm();
        System.out.println(ObservableModelImpl.getInstance().getTurns().size());
        int size = ObservableModelImpl.getInstance().getTurns().size();
        ArrayList<Node> lastNodes = ObservableModelImpl.getInstance().getTurns().get(size-1).nodes;
    }
}
