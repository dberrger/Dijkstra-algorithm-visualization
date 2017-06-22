package model;

public class MainModelImpl implements MainModel{
    private DijkstraModel dijkstraModel;
    private DrawModel drawModel;
    public MainModelImpl(){
        drawModel = new DrawModel();

    }
    @Override
    public void onNextTurn() {
        drawModel.drawGraph(10);
        ObservableModelImpl.getInstance().setMessage("NEXT STEP");
    }

    @Override
    public void onPrevTurn() {
        drawModel.drawGraph(9);
        ObservableModelImpl.getInstance().setMessage("PREV STEP");
    }

    @Override
    public void deleteNode() {

    }

    @Override
    public void addNode() {

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
