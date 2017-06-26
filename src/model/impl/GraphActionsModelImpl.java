package model.impl;

import model.GraphActionsModel;
import structures.Edge;
import structures.Graph;
import structures.Node;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class GraphActionsModelImpl implements GraphActionsModel {
    //TODO REPLACE WITH CLONEABLE
    //FULL COPY NODE
    private Node copyNode(Node nodeToCopy){
        Node n = new Node(nodeToCopy.index);
        n.in=nodeToCopy.in;
        n.out=nodeToCopy.out;
        n.dist=nodeToCopy.dist;
        n.prev=nodeToCopy.prev;
        n.x=nodeToCopy.x;
        n.y=nodeToCopy.y;
        ArrayList<Edge> edges = new ArrayList<>();
        for (Edge e : nodeToCopy.edges){
            edges.add(copyEdge(e));
        }
        n.edges=edges;
        return n;
    }
    //FULL COPY EDGE
    private Edge copyEdge(Edge edgeToCopy){
        Edge edge = new Edge(edgeToCopy.first, edgeToCopy.second, edgeToCopy.weight);
        edge.color=edgeToCopy.color;
        return edge;
    }
    //FULL GRAPH COPY
    @Override
    public Graph graphCopy(Graph graphToCopy){
        Graph graph = new Graph();
        ArrayList<Node> nodes = new ArrayList<>();
        for (Node n: graphToCopy.nodes){
            nodes.add(copyNode(n));
        }
        graph.nodes=nodes;
        return graph;
    }

    @Override
    public Graph readGraphFromFile(File f) {
        //DR TO GET XY FOR NODES
        DrawModelImpl dr = new DrawModelImpl();
        Graph g = new Graph();
        g.nodes=new ArrayList<>();
        int [][] graph = getGraphArrayFromFile(f);
        for (int i=0;i<graph.length;i++){
            g.nodes.add(new Node(i));
        }
        dr.getNodesXY(g);
        for (int i=0;i<graph.length;i++){
            for (int j=0;j<graph.length;j++){
                if (graph[i][j]!=0){
                    g.nodes.get(i).edges.add(new Edge(g.nodes.get(i),g.nodes.get(j),graph[i][j]));
                }
            }
        }
        return graphCopy(g);
    }
    private int[][] getGraphArrayFromFile(File f){
        int [][] graph = new int[0][0];
        Scanner input;
        try {
            input = new Scanner(new File(f.getAbsolutePath()));
            int V = input.nextInt();
            graph = new int[V][V];
            for(int i = 0; i < V; ++i)
            {
                for(int j = 0; j < V; ++j)
                {
                    if(input.hasNextInt())
                    {
                        graph[i][j] = input.nextInt();
                    }
                }
            }
            return graph;
        } catch (Exception e) {
            ObservableModelImpl.getInstance().setMessage("INPUT CORRECT PARAMETERS IN FILE");
        }
        return graph;
    }
}
