package model;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class DrawModel {
    private Pane graphPane;

    public DrawModel() {
        graphPane= new Pane();

    }
    public void drawGraph(int x){
        getNodesXY(x);
        ObservableModelImpl.getInstance().setGraph(graphPane);
    }
    private void getNodesXY(int nodeCount){
        for (int i=0;i<nodeCount;i++){
            float angle = (float) (2.0 * 3.1415926 * i / nodeCount);
            float dx = (float) (200 * Math.cos(angle));
            float dy = (float) (200 * Math.sin(angle));
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
}
