package model;

import structures.Graph;

import java.io.File;

public interface GraphActionsModel {
    //FULL GRAPH COPY
    Graph graphCopy(Graph graphToCopy);
    //READ GRAPH FROM FILE
    Graph readGraphFromFile(File f);
}
