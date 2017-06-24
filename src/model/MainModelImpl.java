package model;

import structures.Node;

public class MainModelImpl implements MainModel{
    private DrawModelImpl drawModel;
    private DijkstraAlgorithmModel dijkstraAlgorithmModel;
    public MainModelImpl(){
        drawModel = new DrawModelImpl();
        dijkstraAlgorithmModel= new DijkstraAlgorithmModel();
    }
    @Override
    public void onNextTurn() {
        //drawModel.drawGraph(10);
        ObservableModelImpl.getInstance().setMessage("NEXT STEP");
    }

    @Override
    public void onPrevTurn() {
        //drawModel.drawGraph(9);
        ObservableModelImpl.getInstance().setMessage("PREV STEP");
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
        dijkstraAlgorithmModel.DijkstraAlgorithm();
        System.out.println(ObservableModelImpl.getInstance().getTurns().size());

    }
}
