package model;

import structures.Graph;

import java.util.ArrayList;

public class DijkstraModel {
    private ArrayList<Graph> turns = new ArrayList<>();
    private Graph initialGraph;
    private int currentTurn=0;
    private static DijkstraModel instance;

    private DijkstraModel() {

    }
    public static DijkstraModel getInstance() {
        DijkstraModel localInstance = instance;
        if (localInstance == null) {
            synchronized (DijkstraModel.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DijkstraModel();
                }
            }
        }
        return localInstance;
    }
    public void nextTurn(){
        currentTurn++;
    }
    public void prevTurn(){
        currentTurn--;
    }
    public void setTurns(ArrayList<Graph> turns){
        this.turns=turns;
    }
    public void setInitialGraph(Graph g){
        turns.clear();
        currentTurn=0;
        this.initialGraph=g;
    }
    public Graph getCurrentTurnGraph(){
        return turns.get(currentTurn);
    }
}
