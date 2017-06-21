package panels;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class GraphPanel {
    private Pane graphPane;
    GraphPanel(){
        graphPane = new Pane();
        graphPane.setCenterShape(true);
        graphPane.setMaxHeight(400);
        graphPane.setMaxWidth(400);
        graphPane.setLayoutX(50);
        graphPane.setLayoutY(50);
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
            float dx = (float) (150 * Math.cos(angle));
            float dy = (float) (150 * Math.sin(angle));
            System.out.println("I " + i + "DX " + dx + " DY " + dy);
            drawCircle(dx,dy);
        }
    }
    private void drawCircle(float x, float y){
        Circle c = new Circle(200+x,200+y,20);
        c.fillProperty();
        graphPane.getChildren().addAll(c);
    }
}
