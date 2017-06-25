package model;

import javafx.scene.layout.Pane;
import panels.GraphPanel;
import panels.TextMessagePanel;
import structures.Edge;
import structures.Graph;
import structures.Node;

import java.util.ArrayList;

public class ObservableModelImpl implements ObservableModel {
    //MESSAGE AND GRAPH PANE TO SET
    private Pane graph;
    private String message;
    //CURRENT TURN
    private int currentTurn=0;
    //INITIAL GRAPH
    private Graph initialGraph;
    //TURNS WITH ARRAY LIST OF GRAPH OBJECT
    private ArrayList<Graph> turns;
    //SHORTEST PATHS STRING
    private String shortestPaths;

    //PANELS TO REGISTER
    private GraphPanel graphPane;
    private TextMessagePanel textMessagePane;

    private static ObservableModelImpl instance;
    //PRIVATE SINGLETON CONSTRUCTOR
    private ObservableModelImpl() {
        //INITIALIZATION
        initialGraph= new Graph();
        graph= new Pane();
        currentTurn=0;
        turns=new ArrayList<>();
        message="Deijkstra Algorithm Visualization";
        shortestPaths="Run Algortithm First";
    }
    //STATIC GET INSTANCE METHOD WITH LAZY INITIALIZATION
    public static ObservableModelImpl getInstance() {
        ObservableModelImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (ObservableModelImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ObservableModelImpl();
                }
            }
        }
        return localInstance;
    }
    //SET GRAPH  -> THEN NOTIFY GRAPH PANEL TO GET THIS PANEL
    public void setGraph(Pane graph){
        this.graph=graph;
        notifyGraphPanel();
    }
    //SET MESSAGE -> THEN NOTIFY TEXT MESSAGE PANEL TO GET THIS MESSAGE
    public void setMessage(String message){
        this.message=message;
        notifyTextMessagePanel();
    }
    //METHOD TO SET SHORTEST PATHS STRING (USAGE IN DIJKSTRA ALGORITHM MODEL TO SET SHORTEST PATHS AFTER RUNNING ALGORITHM
    public void setShortestPaths(String shortestPaths){
        this.shortestPaths=shortestPaths;
    }
    //METHOD TO GET SHORTEST PATHS STRING(USAGE IN CONTROLLER PANEL TO GET SHORTEST PATHS TO SCREEN
    public String getShortestPaths(){
        return this.shortestPaths;
    }
    //ADD NODE TO INITIALGRAPH METHOD
    public void addNodeToInitialGraph(){
        //<15 for DEMONSTRATION
        if (initialGraph.nodes.size()<=15) {
            Node nodeToAdd = new Node(initialGraph.nodes.size());
            nodeToAdd.dist=Integer.MAX_VALUE;
            initialGraph.nodes.add(nodeToAdd);
        } else{
            setMessage("FOR DEMONSTRATION ONLY 15 NODES");
        }
    }
    public void addEdgeToInitialGraph(int first, int second, int weight){
        //CHECK THAT THIS EDGE IS NOT EXISTED
        for (Edge e : initialGraph.nodes.get(first).edges){
            if (e.first.index == first && e.second.index == second){
                setMessage("THIS EDGE ALREADY EXISTS");
                return;
            }
        }
        //CHECK THAT FIRST NODE != SECOND FOR EDGE TO (REMOVE THE POSSIBILITY OF CYCLES IN THE GRAPH)
        if (first==second){
            setMessage("THERE ARE NO CYCLES IN DIJKSTRA ALGORITHM");
            return;
        }
        initialGraph.nodes.get(first).edges.add( new Edge(initialGraph.nodes.get(first),initialGraph.nodes.get(second),weight));
    }
    //CLEAR TURNS METHOD IF SOMETHING IS CHANGED (NODE ADDED OR EDGE ADDED EDITED ETC.)
    public void clearTurns(){
        turns.clear();
    }
    //SET TURNS (USAGE DIJKSTRA ALGORITHM MODEL)
    public void setTurns(ArrayList<Graph> turns){
        this.turns= turns;
    }
    //GET GRAPH TO GRAPH PANEL USAGE GRAPH PANEL(OBSERVER)
    public Pane getGraph(){
        return graph;
    }
    //GET MESSAGE TO TEXT MESSAGE PANEL USAGE TEXT MESSAGE PANEL PANEL(OBSERVER)
    public String getMessage(){
        return message;
    }
    //GET CURRENT TURN GRAPH TO DRAW USAGE DRAW MODEL
    public Graph getCurrentTurnGraph(){
        try {
            return turns.get(currentTurn);
        }
        catch (IndexOutOfBoundsException e){
            setMessage("ADD GRAPH FIRST THEN RUN ALGORITHM");
            return initialGraph;
        }
    }
    //GET INITIAL GRAPH FOR DIJKSTRA ALGORITHM USAGE DIJKSTRA ALGORITHM MODEL
    public Graph getInitialGraph(){
        return initialGraph;
    }
    //NEXT SECOND TURN METHOD USAGE MAIN MODEL IMPL
    public void nextTurn(){
        if (currentTurn+1<turns.size()) {
            currentTurn++;
        }
    }
    public void prevTurn(){
        if (currentTurn-1>0) {
            currentTurn--;
        }
    }
    //REGISTER TEXT PANEL
    @Override
    public void registerTextPanel(TextMessagePanel textMessagePanel) {
        this.textMessagePane=textMessagePanel;
    }
    //REGISTER GRAPH PANEL
    @Override
    public void registerGraphPanel(GraphPanel graphPanel) {
        this.graphPane=graphPanel;
    }
    //NOTIFY TEXT MESSAGE PANEL (WHEN MESSAGE CHANGES -> textMessagePanel use getMessage of this class -> View Change)
    @Override
    public void notifyTextMessagePanel() {
        this.textMessagePane.update();
    }
    //NOTIFY GRAPH PANEL (WHEN GRAPH PANE CHANGES -> GRAPH PANEL ue getGraphPane Method of this class -> View Change)
    @Override
    public void notifyGraphPanel() {
        this.graphPane.update();
    }

}
