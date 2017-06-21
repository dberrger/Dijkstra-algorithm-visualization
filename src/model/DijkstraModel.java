package model;

import structures.Graph;

import java.util.ArrayList;

public class DijkstraModel {
    private ArrayList<Graph> turns = new ArrayList<>();
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
    private void setTurns(ArrayList<Graph> turns){
        this.turns=turns;
    }
    public Graph getCurrentTurnGraph(){
        return turns.get(currentTurn);
    }
}
