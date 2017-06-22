package model;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
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
    private int graphPlaceX=400;
    private int graphPlaceY=10;
    private int graphRadius=200;
    private int nodeRadius=20;
    //TODO ARROW WITH LINES
    private int arrowRadius=5;

    public DrawModelImpl() {
        graphPane= new Pane();

    }
    @Override
    public void drawGraph() {
        drawNodes(ObservableModelImpl.getInstance().getCurrentTurnGraph().nodes);
        drawEdges(ObservableModelImpl.getInstance().getCurrentTurnGraph().edges);
        ObservableModelImpl.getInstance().setGraph(graphPane);
    }

    @Override
    public void getNodesXY(int nodeCount) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < nodeCount; i++)
        {
            float angle = (float) (2.0 * 3.1415926 * i / nodeCount);
            float dx = (float) (graphRadius * Math.cos(angle));
            float dy = (float) (graphRadius * Math.sin(angle));
            nodes.add(new Node(graphPlaceX+dx, graphPlaceY+dy,i));
        }
    }

    private void getNodes(int nodeCount){
        for (int i=0;i<nodeCount;i++){
            float angle = (float) (2.0 * 3.1415926 * i / nodeCount);
            float dx = (float) (graphRadius * Math.cos(angle));
            float dy = (float) (graphRadius * Math.sin(angle));
            System.out.println("I " + i + "DX " + dx + " DY " + dy);
            drawCircle(dx,dy);
        }
    }
    private void drawCircle(float x, float y){
        Circle c = new Circle(300+x,250+y,20);
        c.setFill(Color.WHITE);
        c.setStroke(Color.BLACK);
        EventHandler<MouseEvent> eventHandler = e -> {
            System.out.println("Hello World") ;
            c.setFill(Color.DARKSLATEBLUE) ;
        };
        Text t = new Text(100,200,"asdasd");
        c.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler) ;
        graphPane.getChildren().addAll(c,t);
    }
    private void drawNodeIndex(Node node){
        Text index = new Text(node.x - (nodeRadius/4), node.y - (nodeRadius/3), String.valueOf(node.index));
        graphPane.getChildren().add(index);
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
        Text nodeWeight = new Text(node.x - nodeRadius *2 *cosa , node.y - nodeRadius*2 *sina, text);
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
    }
    private void drawNodes(ArrayList<Node> nodes){
        for (Node node: nodes){
            drawNodeCircle(node);
            drawNodeIndex(node);
            drawNodeWeight(node);
        }
    }
    private void drawEdges(ArrayList<Edge> edges){
        for (Edge edge: edges){
            drawEdge(edge);
            drawEdgeWeight(edge);
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
                edge.second.y - (nodeRadius+2*arrowRadius)*sina);
        line.setStrokeWidth(2);
        graphPane.getChildren().add(line);
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
