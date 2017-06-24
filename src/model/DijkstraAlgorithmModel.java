package model;

import structures.Graph;
import structures.Node;
import java.util.ArrayList;

public class DijkstraAlgorithmModel {
    private int startPoint;
    public void setStartPoint(int startPoint){
        this.startPoint=startPoint;
    }
    public void DijkstraAlgorithm(){
        Graph graph = ObservableModelImpl.getInstance().getInitialGraph();
        ArrayList<Graph> turns = new ArrayList<>();
        initializeNodesForAlgorithm();
        turns.add(graph);
        for (int count = 0; count < graph.nodes.size() - 1; count++) {
            int i = minDistance(graph.nodes);
            graph.nodes.get(i).in = true;
            turns.add(graph);
            for (int j = 0; j < graph.edges.size(); j++) {
                if (i == graph.edges.get(j).first.index) {
                    graph.edges.get(j).color = true;
                    if (!graph.nodes.get(graph.edges.get(j).second.index).out && graph.nodes.get(i).dist != Integer.MAX_VALUE) {
                        if (graph.nodes.get(i).dist + graph.edges.get(j).weight == graph.nodes.get(graph.edges.get(j).second.index).dist) {
                            graph.nodes.get(graph.edges.get(j).second.index).prev.add(i);
                        }
                        if (graph.nodes.get(i).dist + graph.edges.get(j).weight < graph.nodes.get(graph.edges.get(j).second.index).dist) {
                            graph.nodes.get(graph.edges.get(j).second.index).dist = graph.nodes.get(i).dist + graph.edges.get(j).weight;
                            graph.nodes.get(graph.edges.get(j).second.index).prev.clear();
                            graph.nodes.get(graph.edges.get(j).second.index).prev.add(i);
                        }
                    }
                    turns.add(graph);
                    graph.edges.get(j).color = false;
                }
            }
            graph.nodes.get(i).in = false;
            graph.nodes.get(i).out = true;
            turns.add(graph);
        }
        ObservableModelImpl.getInstance().setTurns(turns);
    }


    public void initializeNodesForAlgorithm(){
        for (int i = 0; i < ObservableModelImpl.getInstance().getInitialGraph().nodes.size(); i++) {
            ObservableModelImpl.getInstance().getInitialGraph().nodes.get(i).out = false;
            ObservableModelImpl.getInstance().getInitialGraph().nodes.get(i).in = false;
            if (ObservableModelImpl.getInstance().getInitialGraph().nodes.get(i).index == startPoint) {
                ObservableModelImpl.getInstance().getInitialGraph().nodes.get(i).dist = 0;
            }
            else {
                ObservableModelImpl.getInstance().getInitialGraph().nodes.get(i).dist = Integer.MAX_VALUE;
            }
        }
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
}
