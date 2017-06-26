package model;

import structures.Graph;

import java.util.ArrayList;

public interface GraphModel {
    //ADD NODE TO INITIAL GRAPH METHOD
    void addNodeToInitialGraph();

    void addEdgeToInitialGraph(int first, int second, int weight);

    void setInitialGraph(Graph g);

    //SET TURN
    void setTurn(int turn);

    //CLEAR TURNS METHOD IF SOMETHING IS CHANGED (NODE ADDED OR EDGE ADDED EDITED ETC.)
    void clearTurns();

    //SET TURNS (USAGE DIJKSTRA ALGORITHM MODEL)
    void setTurns(ArrayList<Graph> turns);

    //GET TURNS (USAGE MAIN MODEL)
    ArrayList<Graph> getTurns();

    //CLEAR INITIAL GRAPH
    void clearInitialGraph();

    //GET CURRENT TURN GRAPH TO DRAW USAGE DRAW MODEL
    Graph getCurrentTurnGraph();

    //GET INITIAL GRAPH FOR DIJKSTRA ALGORITHM USAGE DIJKSTRA ALGORITHM MODEL
    Graph getInitialGraph();

    //NEXT SECOND TURN METHOD USAGE MAIN MODEL IMPL
    void nextTurn();

    void prevTurn();
}
