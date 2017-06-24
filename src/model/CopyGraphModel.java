package model;

import structures.Edge;
import structures.Graph;
import structures.Node;

import java.util.ArrayList;

public class CopyGraphModel {
    //TODO REPLACE WITH CLONEABLE
    public Node copyNode(Node nodeToCopy){
        Node n = new Node(nodeToCopy.index);
        n.in=nodeToCopy.in;
        n.out=nodeToCopy.out;
        n.dist=nodeToCopy.dist;
        n.prev=nodeToCopy.prev;
        n.x=nodeToCopy.x;
        n.y=nodeToCopy.y;
        return n;
    }
    public Edge copyEdge(Edge edgeToCopy){
        Edge edge = new Edge(edgeToCopy.first, edgeToCopy.second, edgeToCopy.weight);
        edge.color=edgeToCopy.color;
        return edge;
    }
    public Graph graphCopy(Graph graphToCopy){
        Graph graph = new Graph();
        ArrayList<Node> nodes = new ArrayList<>();
        ArrayList<Edge> edges = new ArrayList<>();
        for (Node n: graphToCopy.nodes){
            nodes.add(copyNode(n));
        }
        for (Edge e: graphToCopy.edges){
            edges.add(copyEdge(e));
        }
        graph.nodes=nodes;
        graph.edges=edges;
        return graph;
    }
}
