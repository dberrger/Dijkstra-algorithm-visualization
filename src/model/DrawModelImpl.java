package model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import structures.Edge;
import structures.Node;

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
        drawNodes(ObservableModelImpl.getInstance().getCurrentTurnGraph().nodes);
        drawEdges(ObservableModelImpl.getInstance().getCurrentTurnGraph().nodes);
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
        Circle c= new Circle(node.x,node.y,nodeRadius);
        c.setFill(Color.WHITE);
        c.setStroke(Color.BLACK);
        if (node.out){
            c.setStroke(Color.RED);
        }
        if (node.in){
            c.setStroke(Color.GREEN);
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
        Line line = new Line(edge.first.x + (nodeRadius+2*arrowRadius)*cosa,
                edge.first.y + (nodeRadius+2*arrowRadius)*sina,
                edge.second.x - (nodeRadius+2*arrowRadius)*cosa,
                edge.second.y - (nodeRadius+2*arrowRadius)*sina/3);
        if (edge.color){
            System.out.println("COLOR EDGE " + edge.first.index + " " + edge.second.index + " " + edge.weight);
            line.setStroke(Color.GREEN);
        }
        //TODO REPLACE WITH ARROW

        Circle arrow = new Circle(edge.second.x - (nodeRadius+2*arrowRadius)*cosa,
                edge.second.y - (nodeRadius+2*arrowRadius)*sina,5);
        arrow.setFill(Color.WHITE);
        arrow.setStroke(Color.BLACK);
        graphPane.getChildren().addAll(line,arrow);
    }
    private void drawEdgeWeight(Edge edge){
        float x1 = edge.first.x;
        float y1 = edge.first.y;
        float x2 = edge.second.x;
        float y2 = edge.second.y;
        float srx = (x1 + x2) / 2;
        float sry = (y1 + y2) / 2;
        Text edgeWeight = new Text(srx,sry,String.valueOf(edge.weight));
        graphPane.getChildren().add(edgeWeight);
    }
}
