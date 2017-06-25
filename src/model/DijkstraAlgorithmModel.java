package model;

import structures.Graph;
import structures.Node;
import java.util.ArrayList;
import java.util.List;

public class DijkstraAlgorithmModel {
    private int startPoint;
    private CopyGraphModel copyGraphModel;
    public void setStartPoint(int startPoint){
        this.startPoint=startPoint;
    }
    public DijkstraAlgorithmModel(){
        copyGraphModel= new CopyGraphModel();
    }
    public void DijkstraAlgorithm(){
        Graph graph = ObservableModelImpl.getInstance().getInitialGraph();
        ArrayList<Graph> turns = new ArrayList<>();
        initializeNodesForAlgorithm();
        turns.add(copyGraphModel.graphCopy(graph));
        for (int count = 0; count < graph.nodes.size() - 1; count++) {
            int i = minDistance(graph.nodes);
            graph.nodes.get(i).in = true;
            turns.add(copyGraphModel.graphCopy(graph));
            for (int j = 0; j < graph.nodes.get(i).edges.size(); j++) {
                    graph.nodes.get(i).edges.get(j).color = true;
                    if (!graph.nodes.get(graph.nodes.get(i).edges.get(j).second.index).out && graph.nodes.get(i).dist != Integer.MAX_VALUE) {
                        if (graph.nodes.get(i).dist + graph.nodes.get(i).edges.get(j).weight == graph.nodes.get(graph.nodes.get(i).edges.get(j).second.index).dist) {
                            graph.nodes.get(graph.nodes.get(i).edges.get(j).second.index).prev.add(i);
                        }
                        if (graph.nodes.get(i).dist + graph.nodes.get(i).edges.get(j).weight < graph.nodes.get(graph.nodes.get(i).edges.get(j).second.index).dist) {
                            graph.nodes.get(graph.nodes.get(i).edges.get(j).second.index).dist = graph.nodes.get(i).dist + graph.nodes.get(i).edges.get(j).weight;
                            graph.nodes.get(graph.nodes.get(i).edges.get(j).second.index).prev.clear();
                            graph.nodes.get(graph.nodes.get(i).edges.get(j).second.index).prev.add(i);
                        }
                    }
                    turns.add(copyGraphModel.graphCopy(graph));
                    printGraph(graph);
                    graph.nodes.get(i).edges.get(j).color = false;
            }
            graph.nodes.get(i).in = false;
            graph.nodes.get(i).out = true;
            turns.add(copyGraphModel.graphCopy(graph));
        }
        ObservableModelImpl.getInstance().setTurns(turns);
        getShortestPaths(graph.nodes);
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
    public void printPath(ArrayList<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i) + "<-");
        }
    }
    public void shortestPathToNode(ArrayList<Node> nodes, Node currentNode, ArrayList<Integer> path) {
        if (currentNode.index == startPoint) {
            path.add(startPoint);
            printPath(path);
            path.clear();
            System.out.print( "\n");
            return;
        }
        path.add(currentNode.index);
        for (int i = 0; i < currentNode.prev.size(); i++) {
            shortestPathToNode(nodes,nodes.get(currentNode.prev.get(i)), (ArrayList<Integer>) path.clone());
        }

    }
    public ArrayList<Integer> copyPath(ArrayList<Integer> pathToCopy){
        ArrayList<Integer> pathNew = new ArrayList<>();
        for (int i=0;i<pathToCopy.size();i++){
            int val = pathToCopy.get(i);
            pathNew.add(val);
        }
        return pathNew;
    }
    public void getShortestPaths(ArrayList<Node> nodes) {
        ArrayList<Integer> path = new ArrayList<>();
        for(int i = 0; i < nodes.size(); i++) {
            System.out.println ("SHORTEST PATHS TO NODE NUMBER " + (nodes.get(i).index));
            shortestPathToNode(nodes,  nodes.get(i),(ArrayList<Integer>) path.clone());
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
    private void printGraph(Graph g){
        for (Node n : g.nodes){
            System.out.println(n.dist);
        }
    }
}
