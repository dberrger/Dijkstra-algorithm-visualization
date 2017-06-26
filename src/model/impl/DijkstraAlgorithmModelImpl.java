package model.impl;

import model.DijkstraAlgorithmModel;
import structures.Graph;
import structures.Node;
import java.util.ArrayList;

public class DijkstraAlgorithmModelImpl implements DijkstraAlgorithmModel {
    //StartPoint
    private int startPoint;
    //ObjectForGraphCopy
    private GraphActionsModelImpl copyGraphModel;
    //String for ShortestPaths(to textMessagePanel)
    private String shortestPaths="";
    @Override
    public void setStartPoint(int startPoint){
        //StartPoint
        this.startPoint=startPoint;
    }
    public DijkstraAlgorithmModelImpl(){
        //Object for Graph copy
        copyGraphModel= new GraphActionsModelImpl();
    }
    //DIJKSTRA ALGORITHM MAKE turns.add to save STEP
    @Override
    public ArrayList<Graph> DijkstraAlgorithm(Graph graphForAlgorithm){
        shortestPaths="";
        initializeNodesForAlgorithm(graphForAlgorithm.nodes);
        Graph graph = copyGraphModel.graphCopy(graphForAlgorithm);
        ArrayList<Graph> turns = new ArrayList<>();
        turns.add(copyGraphModel.graphCopy(graph));
        for (int count = 0; count < graph.nodes.size() - 1; count++) {
            int i = minDistance(graph.nodes);
            if (i==-1){
                break;
            }
            graph.nodes.get(i).in = true;
            turns.add(copyGraphModel.graphCopy(graph));
            for (int j = 0; j < graph.nodes.get(i).edges.size(); j++) {
                    graph.nodes.get(i).edges.get(j).color = true;
                    if (!graph.nodes.get(graph.nodes.get(i).edges.get(j).second.index).out) {
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
                    graph.nodes.get(i).edges.get(j).color = false;
            }
            graph.nodes.get(i).in = false;
            graph.nodes.get(i).out = true;
            turns.add(copyGraphModel.graphCopy(graph));
        }
        return turns;
    }
    //INITIALIZING NODES FOR ALGORITHM ALL IN=FALSE OUT=FALSE FALSE DIST=INFINITY except start point
    private void initializeNodesForAlgorithm(ArrayList<Node> nodes){
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
    }
    //ADD PATH TO shortestPaths
    private void addPath(ArrayList<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            shortestPaths+=path.get(i)+"<-";
        }
        shortestPaths+="\n";
    }
    //RECURSION GET PATHS TO EVERY NODE
    private void shortestPathToNode(ArrayList<Node> nodes, Node currentNode, ArrayList<Integer> path) {
        if (currentNode.index == startPoint) {
            path.add(startPoint);
            addPath(path);
            path.clear();
            return;
        }
        path.add(currentNode.index);
        for (int i = 0; i < currentNode.prev.size(); i++) {
            shortestPathToNode(nodes,nodes.get(currentNode.prev.get(i)), (ArrayList<Integer>) path.clone());
        }
    }
    //GET PATH OF EVERY NODE
    @Override
    public String getShortestPaths(ArrayList<Node> nodes) {
        ArrayList<Integer> path = new ArrayList<>();
        for(int i = 0; i < nodes.size(); i++) {
            shortestPaths+="SHORTEST PATHS TO NODE NUMBER " + (nodes.get(i).index) + "\n";
            shortestPathToNode(nodes,  nodes.get(i),(ArrayList<Integer>) path.clone());
        }
        return shortestPaths;
    }
    //GET MIN DISTANCE INDEX TO NODE
    private int minDistance(ArrayList<Node> nodes){
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < nodes.size(); i++)
            if (!nodes.get(i).out && nodes.get(i).dist <= min) {
                min = nodes.get(i).dist;
                index = i;
            }
        if (min==Integer.MAX_VALUE){
            return -1;
        }
        return index;
    }
}
