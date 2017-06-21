package panels;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;


public class MainPanel {
    private BorderPane rootPane;
    public MainPanel(){
        rootPane= new BorderPane();
        GraphPanel graphPanel = new GraphPanel();
        ControllerPanel controllerPanel = new ControllerPanel();
        graphPanel.drawGraph();
        rootPane.setLeft(graphPanel.getPanel());
        rootPane.setRight(controllerPanel.getPanel());
        rootPane.setMargin(controllerPanel.getPanel(),new Insets(50.0,100.0,0.0,0.0));
    }
    public BorderPane getPanel(){
        return rootPane;
    }
}
