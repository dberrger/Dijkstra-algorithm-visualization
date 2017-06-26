package model;


import structures.Graph;
import structures.Node;

import java.util.ArrayList;

public interface DijkstraAlgorithmModel {
    void setStartPoint(int startPoint);

    //DIJKSTRA ALGORITHM MAKE turns.add to save STEP
    ArrayList<Graph> DijkstraAlgorithm(Graph graphForAlgorithm);

    //GET PATH OF EVERY NODE
    String getShortestPaths(ArrayList<Node> nodes);
}
