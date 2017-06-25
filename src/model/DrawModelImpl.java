package model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import structures.Edge;
import structures.Node;

import java.awt.*;
import java.util.ArrayList;

public class DrawModelImpl implements DrawModel{
    private Pane graphPane;
    //TODO CONSTANTS
    private int graphPlaceX=300;
    private int graphPlaceY=200;
    private int graphRadius=150;
    private int nodeRadius=20;
    //TODO ARROW WITH LINES
    private int arrowRadius=5;

    public DrawModelImpl() {
        graphPane= new Pane();

    }
    @Override
    public void drawGraph() {
        drawEdges(ObservableModelImpl.getInstance().getCurrentTurnGraph().nodes);
        drawNodes(ObservableModelImpl.getInstance().getCurrentTurnGraph().nodes);
        ObservableModelImpl.getInstance().setGraph(graphPane);
    }
    public void drawInitialGraph(){
        drawNodes(ObservableModelImpl.getInstance().getInitialGraph().nodes);
        drawEdges(ObservableModelImpl.getInstance().getInitialGraph().nodes);
        ObservableModelImpl.getInstance().setGraph(graphPane);
    }
    @Override
    public void getNodesXY() {
        for (int i = 0; i < ObservableModelImpl.getInstance().getInitialGraph().nodes.size(); i++)
        {
            float angle = (float) (2.0 * 3.1415926 * i / ObservableModelImpl.getInstance().getInitialGraph().nodes.size());
            float dx = (float) (graphRadius * Math.cos(angle));
            float dy = (float) (graphRadius * Math.sin(angle));
            ObservableModelImpl.getInstance().getInitialGraph().nodes.get(i).x=graphPlaceX+dx;
            ObservableModelImpl.getInstance().getInitialGraph().nodes.get(i).y=graphPlaceY+dy;

        }
    }
    private void drawNodeIndex(int index, double x, double y){
        Text nodeIndex = new Text(x-4, y+2, String.valueOf(index));
        graphPane.getChildren().add(nodeIndex);
    }
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
        //TODO CHANGE
        Text nodeWeight = new Text(node.x - nodeRadius *1.7 *cosa , node.y - nodeRadius*1.7 *sina, text);
        graphPane.getChildren().add(nodeWeight);
    }
    private void drawNodeCircle(Node node){
        Circle c = new Circle(node.x,node.y,nodeRadius);
        c.setFill(Color.WHITE);
        c.setStroke(Color.BLACK);
        if (node.out){
            c.setStroke(Color.RED);
            c.setStrokeWidth(3);
        }
        if (node.in){
            c.setStroke(Color.GREEN);
            c.setStrokeWidth(3);
        }
        graphPane.getChildren().add(c);
        drawNodeIndex(node.index, c.getCenterX(), c.getCenterY());
    }
    private void drawNodes(ArrayList<Node> nodes){
        for (Node node: nodes){
            drawNodeCircle(node);
            drawNodeWeight(node);
        }
    }
    private void drawEdges(ArrayList<Node> nodes){
        for (Node node: nodes){
            for (Edge edge:node.edges) {
                drawEdge(edge);
                drawEdgeWeight(edge);
            }
        }
    }
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
        //TODO REPLACE WITH ARROW

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
            //System.out.println("COLOR EDGE " + edge.first.index + " " + edge.second.index + " " + edge.weight);
            line1.setStroke(Color.GREEN);
            line1.setStrokeWidth(3);
            line2.setStroke(Color.GREEN);
            line2.setStrokeWidth(3);
        }

        graphPane.getChildren().addAll(line, line1, line2);
    }
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
