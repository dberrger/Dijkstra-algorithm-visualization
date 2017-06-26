package model.impl;

import model.GraphModel;
import structures.Edge;
import structures.Graph;
import structures.Node;

import java.util.ArrayList;

public class GraphModelImpl implements GraphModel {

    //CURRENT TURN
    private int currentTurn;
    //INITIAL GRAPH
    private Graph initialGraph;
    //TURNS WITH ARRAY LIST OF GRAPH OBJECT
    private ArrayList<Graph> turns;
    public GraphModelImpl(){
        currentTurn=0;
        initialGraph = new Graph();
        turns= new ArrayList<>();
    }
    //ADD NODE TO INITIAL GRAPH METHOD
    @Override
    public void addNodeToInitialGraph(){
        //<15 for DEMONSTRATION
        if (initialGraph.nodes.size()<=15) {
            Node nodeToAdd = new Node(initialGraph.nodes.size());
            nodeToAdd.dist=Integer.MAX_VALUE;
            initialGraph.nodes.add(nodeToAdd);
        } else{
            ObservableModelImpl.getInstance().setMessage("FOR DEMONSTRATION ONLY 15 NODES");
        }
    }
    @Override
    public void addEdgeToInitialGraph(int first, int second, int weight){
        //CHECK THAT THIS EDGE IS NOT EXISTED
        for (Edge e : initialGraph.nodes.get(first).edges){
            if (e.first.index == first && e.second.index == second){
                ObservableModelImpl.getInstance().setMessage("THIS EDGE ALREADY EXISTS");
                return;
            }
        }
        //CHECK THAT FIRST NODE != SECOND FOR EDGE TO (REMOVE THE POSSIBILITY OF CYCLES IN THE GRAPH)
        if (first==second){
            ObservableModelImpl.getInstance().setMessage("THERE ARE NO CYCLES IN DIJKSTRA ALGORITHM");
            return;
        }
        initialGraph.nodes.get(first).edges.add( new Edge(initialGraph.nodes.get(first),initialGraph.nodes.get(second),weight));
    }
    //SET TURN
    @Override
    public void setTurn(int turn){
        this.currentTurn=turn;
    }
    //CLEAR TURNS METHOD IF SOMETHING IS CHANGED (NODE ADDED OR EDGE ADDED EDITED ETC.)
    @Override
    public void clearTurns(){
        turns.clear();
        currentTurn=0;
    }
    //SET TURNS (USAGE DIJKSTRA ALGORITHM MODEL)
    @Override
    public void setTurns(ArrayList<Graph> turns){
        this.turns= turns;
    }
    //GET TURNS (USAGE MAIN MODEL)
    @Override
    public ArrayList<Graph> getTurns() {
        return this.turns;
    }

    //CLEAR INITIAL GRAPH
    @Override
    public void clearInitialGraph(){
        initialGraph.nodes.clear();
    }
    //GET CURRENT TURN GRAPH TO DRAW USAGE DRAW MODEL
    @Override
    public Graph getCurrentTurnGraph(){
        try {
            return turns.get(currentTurn);
        }
        catch (IndexOutOfBoundsException e){
            ObservableModelImpl.getInstance().setMessage("ADD GRAPH FIRST THEN RUN ALGORITHM");
            return initialGraph;
        }
    }
    //GET INITIAL GRAPH FOR DIJKSTRA ALGORITHM USAGE DIJKSTRA ALGORITHM MODEL
    @Override
    public Graph getInitialGraph(){
        return initialGraph;
    }
    //NEXT SECOND TURN METHOD USAGE MAIN MODEL IMPL
    @Override
    public void nextTurn(){
        if (currentTurn+1<turns.size()) {
            currentTurn++;
        }
    }
    @Override
    public void prevTurn(){
        if (currentTurn-1>0) {
            currentTurn--;
        }
    }
}
