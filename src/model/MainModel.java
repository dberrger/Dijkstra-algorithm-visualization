package model;

import java.io.File;

public interface MainModel {
    //MAKE NEXT TURN
    void onNextTurn();
    //MAKE PREV TURN
    void onPrevTurn();
    //ADD NODE
    void addNode();
    //ADD EDGE
    void addEdge(int first, int second, int weight);
    //RUN ALGORITHM ON INITIAL GRAPH
    void runAlgorithm(int i);
    //TO LAST STEP
    void toLastStep();
    //TO FIRST STEP
    void toFirstStep();
    //CLEAR GRAPH
    void clearGraph();
    //READ GRAPH FROM FILE
    void readGraphFromFile(File file);
}
