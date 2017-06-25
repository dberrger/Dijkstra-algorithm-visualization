package model;

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
    }

    @Override
    public void onPrevTurn() {
        ObservableModelImpl.getInstance().prevTurn();
        drawModel.drawGraph();
    }

    @Override
    public void addNode() {
        ObservableModelImpl.getInstance().clearTurns();
        ObservableModelImpl.getInstance().setMessage("NEW NODE ADDED");
        ObservableModelImpl.getInstance().addNodeToInitialGraph();
        drawModel.getNodesXY();
        drawModel.drawInitialGraph();
    }

    @Override
    public void addEdge(int first, int second, int weight) {
        ObservableModelImpl.getInstance().clearTurns();
        ObservableModelImpl.getInstance().addEdgeToInitialGraph(first,second,weight);
        drawModel.drawInitialGraph();
    }

    @Override
    public void runAlgorithm() {
        System.out.println("LETS RUN ALGORITHM");
        dijkstraAlgorithmModel.setStartPoint(0);
        dijkstraAlgorithmModel.DijkstraAlgorithm();
        System.out.println(ObservableModelImpl.getInstance().getTurns().size());
    }
}
