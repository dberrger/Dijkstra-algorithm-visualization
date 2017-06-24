package model;

public interface MainModel {
    void onNextTurn();
    void onPrevTurn();
    void deleteNode();
    void addNode();
    void addEdge(int first, int second, int weight);
    void deleteEdge();
    void runAlgorithm();
}
