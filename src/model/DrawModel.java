package model;

import javafx.scene.layout.Pane;
import structures.Graph;

public interface DrawModel {
    //DRAW GRAPH ON THIS TURN
    Pane drawGraph(Graph graph);
    //GET XY NODES TO INITIAL GRAPH
    void getNodesXY(Graph graph);
}
