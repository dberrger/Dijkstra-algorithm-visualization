package model;

import structures.Node;

public class MainModelImpl implements MainModel{
    private DrawModelImpl drawModel;
    public MainModelImpl(){
        drawModel = new DrawModelImpl();
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
    public void addEdge() {

    }

    @Override
    public void deleteEdge() {

    }

    @Override
    public void runAlgorithm() {

    }
}
