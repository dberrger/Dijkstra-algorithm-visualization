package model;

public interface MainModel {
    void onNextTurn();
    void onPrevTurn();
    void addNode();
    void addEdge(int first, int second, int weight);
    void runAlgorithm();
}
