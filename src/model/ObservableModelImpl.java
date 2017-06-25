package model;

import javafx.scene.layout.Pane;
import panels.GraphPanel;
import panels.TextMessagePanel;
import structures.Edge;
import structures.Graph;
import structures.Node;

import java.util.ArrayList;

public class ObservableModelImpl implements ObservableModel {
    private Pane graph;
    private String message;
    private GraphPanel graphPane;
    private TextMessagePanel textMessagePane;
    private int currentTurn=0;
    private Graph initialGraph= new Graph();
    private ArrayList<Graph> turns;
    private String shortestPaths;

    private static ObservableModelImpl instance;

    private ObservableModelImpl() {
        graph= new Pane();
        currentTurn=0;
        turns=new ArrayList<>();
        message="Deijkstra Algorithm Visualization";
        shortestPaths="Run Algortithm First";
    }
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
    public void setGraph(Pane graph){
        this.graph=graph;
        notifyGraphPanel();
    }
    public void setMessage(String message){
        this.message=message;
        notifyTextMessagePanel();
    }
    public void setShortestPaths(String shortestPaths){
        this.shortestPaths=shortestPaths;
    }
    public String getShortestPaths(){
        return this.shortestPaths;
    }
    public void addNodeToInitialGraph(){
        initialGraph.nodes.add(new Node(initialGraph.nodes.size()));
    }
    public void addEdgeToInitialGraph(int first, int second, int weight){
        initialGraph.nodes.get(first).edges.add( new Edge(initialGraph.nodes.get(first),initialGraph.nodes.get(second),weight));
    }
    public ArrayList<Graph> getTurns(){
        return turns;
    }
    public void setTurns(ArrayList<Graph> turns){
        this.turns= turns;
    }
    public Pane getGraph(){
        return graph;
    }
    public String getMessage(){
        return message;
    }
    public Graph getCurrentTurnGraph(){
        try {
            return turns.get(currentTurn);
        }
        catch (IndexOutOfBoundsException e){
            setMessage("ADD GRAPH FIRST THEN RUN ALGORITHM");
            return initialGraph;
        }
    }
    public Graph getInitialGraph(){
        return initialGraph;
    }
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

    @Override
    public void registerTextPanel(TextMessagePanel textMessagePanel) {
        this.textMessagePane=textMessagePanel;
    }

    @Override
    public void registerGraphPanel(GraphPanel graphPanel) {
        this.graphPane=graphPanel;
    }

    @Override
    public void notifyTextMessagePanel() {
        this.textMessagePane.update();
    }

    @Override
    public void notifyGraphPanel() {
        this.graphPane.update();
    }

}
