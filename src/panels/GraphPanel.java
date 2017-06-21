package panels;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GraphPanel {
    private Pane graphPane;
    GraphPanel(){
        graphPane = new Pane();
        graphPane.setCenterShape(true);
        graphPane.setMinSize(600,400);
        graphPane.setMaxSize(700,500);
        String style = "-fx-background-color: ghostwhite";
        graphPane.setStyle(style);
    }

    public Pane getPanel(){
        return graphPane;
    }
    public void clearGraph(){
        graphPane.getChildren().clear();
    }
    public void drawGraph(){
        getNodesXY(10);
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
        c.fillProperty();
        graphPane.getChildren().addAll(c);
    }
}
