package model.impl;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import model.DrawModel;
import structures.Edge;
import structures.Graph;
import structures.Node;

import java.util.ArrayList;

public class DrawModelImpl implements DrawModel {
    //GRAPH PANE FOR TO MAKE DRAW METHODS
    private Pane graphPane;
    //GRAPH PLACE COORDINATES
    private int graphPlaceX=300;
    private int graphPlaceY=200;
    //RADIUS OF NODE
    private int nodeRadius=20;
    public DrawModelImpl() {
        //INITIALIZE GRAPH PANE MAKE GRAPH PANE CLASS TO MAKE DRAW METHODS
        graphPane= new Pane();
    }
    //DRAW GRAPH ON THIS TURN
    @Override
    public Pane drawGraph(Graph graph) {
        drawEdges(graph.nodes);
        drawNodes(graph.nodes);
        return graphPane;
    }

    //GET XY NODES TO INITIAL GRAPH
    @Override
    public void getNodesXY(Graph graph) {
        for (int i = 0; i < graph.nodes.size(); i++)
        {
            float angle = (float) (2.0 * 3.1415926 * i / graph.nodes.size());
            int graphRadius = 150;
            float dx = (float) (graphRadius * Math.cos(angle));
            float dy = (float) (graphRadius * Math.sin(angle));
            graph.nodes.get(i).x=graphPlaceX+dx;
            graph.nodes.get(i).y=graphPlaceY+dy;
        }
    }
    //DRAW NODE INDEX
    private void drawNodeIndex(int index, double x, double y){
        Text nodeIndex = new Text(x-4, y+2, String.valueOf(index));
        graphPane.getChildren().add(nodeIndex);
    }
    //DRAW NODE WEIGHT
    private void drawNodeWeight(Node node) {
        float b = graphPlaceX - node.x;
        float a = graphPlaceY - node.y;
        float c = (float) Math.sqrt(a*a + b*b);
        float sina = a / c;
        float cosa = b / c;
        String text;
        if (node.dist == Integer.MAX_VALUE) {
            text = "?";
        }
        else {
            text = String.valueOf(node.dist);
        }
        Text nodeWeight = new Text(node.x - nodeRadius *1.7 *cosa , node.y - nodeRadius*1.7 *sina, text);
        graphPane.getChildren().add(nodeWeight);
    }
    //DRAW NODE CIRCLE
    private void drawNodeCircle(Node node){
        Circle c = new Circle(node.x,node.y,nodeRadius);
        c.setFill(Color.WHITE);
        c.setStroke(Color.BLACK);
        //IF NODE IS OUT THEN MAKE IT RED COLOR
        if (node.out){
            c.setStroke(Color.RED);
            c.setStrokeWidth(3);
        }
        //IF NODE IS IN THEN MAKE IT GREEN COLOR
        if (node.in){
            c.setStroke(Color.GREEN);
            c.setStrokeWidth(3);
        }
        graphPane.getChildren().add(c);
        drawNodeIndex(node.index, c.getCenterX(), c.getCenterY());
    }
    //DRAW NODES WITH ITERATION OF NODES ARRAY LIST
    private void drawNodes(ArrayList<Node> nodes){
        for (Node node: nodes){
            drawNodeCircle(node);
            drawNodeWeight(node);
        }
    }
    //DRAW EDGES WITH ITERATION OF NODES THEN GET EDGES OF NODE
    private void drawEdges(ArrayList<Node> nodes){
        //ITERATE NODES
        for (Node node: nodes){
            //DRAW EDGES OF NODE
            for (Edge edge:node.edges) {
                drawEdge(edge);
                drawEdgeWeight(edge);
            }
        }
    }
    //DRAW EDGE
    private void drawEdge(Edge edge){
        float b = edge.second.x - edge.first.x;
        float a = edge.second.y - edge.first.y;
        float c = (float) Math.sqrt(a*a + b*b);
        float sina = a / c;
        float cosa = b / c;
        Line line = new Line(edge.first.x + nodeRadius*cosa,
                edge.first.y + nodeRadius*sina,
                edge.second.x - nodeRadius*cosa,
                edge.second.y - nodeRadius*sina);
        if (edge.color){
            line.setStroke(Color.GREEN);
            line.setStrokeWidth(3);
        }

        //стрелочки
        double ostr = 0.25; //острота стрелки
        double petal = c / 15; //длина лепестков
        double angle = Math.atan(a/b); //угол стрелки
        double coordinateX = edge.second.x - nodeRadius*cosa;
        double coordinateY = edge.second.y - nodeRadius*sina;

        int i = 1;
        if (edge.second.x < edge.first.x) {
            i = -1;
        }
        Line line1 = new Line(coordinateX, coordinateY,
                coordinateX - i*petal*Math.cos(angle-ostr),
                coordinateY - i*petal*Math.sin(angle-ostr));
        Line line2 = new Line (coordinateX, coordinateY,
                coordinateX - i*petal*Math.cos(angle+ostr),
                coordinateY - i*petal*Math.sin(angle+ostr));
        if (edge.color){
            line1.setStroke(Color.GREEN);
            line1.setStrokeWidth(3);
            line2.setStroke(Color.GREEN);
            line2.setStrokeWidth(3);
        }
        graphPane.getChildren().addAll(line, line1, line2);
    }
    //DRAW EDGE WEIGHT
    private void drawEdgeWeight(Edge edge){
        float x1 = edge.first.x;
        float y1 = edge.first.y;
        float x2 = edge.second.x;
        float y2 = edge.second.y;
        float srx = (x1 + x2) / 2;
        float sry = (y1 + y2) / 2;

        Circle c = new Circle(srx, sry, 15);
        c.setFill(Color.LIGHTGRAY);
        c.setStroke(Color.BLACK);

        Text edgeWeight = new Text(srx-5,sry+5,String.valueOf(edge.weight));
        graphPane.getChildren().addAll(c, edgeWeight);
    }
}
