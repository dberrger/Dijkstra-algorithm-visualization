package model;

public class MainModelImpl implements MainModel{
    //DRAW MODEL OBJECT
    private DrawModelImpl drawModel;
    //DIJKSTRA ALGORITHM OBJECT
    private DijkstraAlgorithmModel dijkstraAlgorithmModel;
    public MainModelImpl(){
        //DRAW MODEL AND DIJKSTRA ALGORITHM OBJECT INITIALIZATION
        drawModel = new DrawModelImpl();
        dijkstraAlgorithmModel= new DijkstraAlgorithmModel();
    }
    //NEXT TURN METHOD
    @Override
    public void onNextTurn() {
        ObservableModelImpl.getInstance().nextTurn();
        drawModel.drawGraph();
    }
    //PREV TURN METHOD
    @Override
    public void onPrevTurn() {
        ObservableModelImpl.getInstance().prevTurn();
        drawModel.drawGraph();
    }
    //ADD NODE METHOD
    @Override
    public void addNode() {
        //CLEAR TURNS
        ObservableModelImpl.getInstance().clearTurns();
        //SET MESSAGE
        ObservableModelImpl.getInstance().setMessage("NEW NODE ADDED");
        //ADD NODE TO INITIAL GRAPH OF SINGLETON
        ObservableModelImpl.getInstance().addNodeToInitialGraph();
        //GET XY COORDINATE OF INITIAL GRAPH NODES
        drawModel.getNodesXY();
        //DRAW INITIAL GRAPH
        drawModel.drawInitialGraph();
    }

    @Override
    public void addEdge(int first, int second, int weight) {
        //CLEAR TURNS
        ObservableModelImpl.getInstance().clearTurns();
        //ADD EDGE TO INITIAL GRAPH
        ObservableModelImpl.getInstance().addEdgeToInitialGraph(first,second,weight);
        //DRAW INITIAL GRAPH
        drawModel.drawInitialGraph();
    }

    @Override
    public void runAlgorithm(int startPoint) {
        //SET START POINT
        dijkstraAlgorithmModel.setStartPoint(startPoint);
        //RUN ALGORITHM ON INITIAL GRAPH
        dijkstraAlgorithmModel.DijkstraAlgorithm();
    }
}
