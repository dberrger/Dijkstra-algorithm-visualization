package model;

import structures.Edge;
import structures.Graph;
import structures.Node;

import java.util.ArrayList;
import java.util.List;

public class DijkstraAlgorithmModel {
    private int startPoint;
    public void setStartPoint(int startPoint){
        this.startPoint=startPoint;
    }
    public void DijkstraAlgorithm(){
        ArrayList<Node> nodes = ObservableModelImpl.getInstance().getInitialGraph().nodes;
        ArrayList<Edge> edges = ObservableModelImpl.getInstance().getInitialGraph().edges;
        ArrayList<Graph> turns = new ArrayList<>();
        nodes = initializeNodesForAlgorithm(nodes);
        turns.add(new Graph(nodes,edges));
        for (int count = 0; count < nodes.size() - 1; count++) {
            int i = minDistance(nodes);
            nodes.get(i).in = true;
            turns.add(new Graph(nodes,edges));
            for (int j = 0; j < edges.size(); j++) {
                if (i == edges.get(j).first.index) {
                    edges.get(j).color = true;
                    if (!nodes.get(edges.get(j).second.index).out && nodes.get(i).dist != Integer.MAX_VALUE) {
                        if (nodes.get(i).dist + edges.get(j).weight == nodes.get(edges.get(j).second.index).dist) {
                            nodes.get(edges.get(j).second.index).prev.add(i);
                        }
                        if (nodes.get(i).dist + edges.get(j).weight < nodes.get(edges.get(j).second.index).dist) {
                            nodes.get(edges.get(j).second.index).dist = nodes.get(i).dist + edges.get(j).weight;
                            nodes.get(edges.get(j).second.index).prev.clear();
                            nodes.get(edges.get(j).second.index).prev.add(i);
                        }
                    }
                    turns.add(new Graph(nodes,edges));
                    edges.get(j).color = false;
                }
            }
            nodes.get(i).in = false;
            nodes.get(i).out = true;
            turns.add(new Graph(nodes, edges));
        }

    }


    public ArrayList<Node> initializeNodesForAlgorithm(ArrayList<Node> nodes){
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).out = false;
            nodes.get(i).in = false;
            if (nodes.get(i).index == startPoint) {
                nodes.get(i).dist = 0;
            }
            else {
                nodes.get(i).dist = Integer.MAX_VALUE;
            }
        }
        return nodes;
    }
    private int minDistance(ArrayList<Node> nodes){
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < nodes.size(); i++)
            if (!nodes.get(i).out && nodes.get(i).dist <= min) {
                min = nodes.get(i).dist;
                index = i;
            }
        return index;
    }
    public void initializeEdgesForAlgorithm(){

    }
}
