package panels;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;


public class MainPanel {
    private BorderPane rootPane;
    public MainPanel(){
        rootPane= new BorderPane();
        GraphPanel graphPanel = new GraphPanel();
        ControllerPanel controllerPanel = new ControllerPanel();
        TextMessagePanel textMessagePanel = new TextMessagePanel();
        rootPane.setLeft(graphPanel.getPanel());
        rootPane.setRight(controllerPanel.getPanel());
        rootPane.setBottom(textMessagePanel.getPanel());
        rootPane.setMargin(controllerPanel.getPanel(),new Insets(50.0,100.0,0.0,15.0));
        rootPane.setMargin(rootPane.getBottom(),new Insets(50.0,0.0,50.0,100.0));
        rootPane.setPadding(new Insets(10,10,10,10));
        textMessagePanel.setMessage("TEST TEXT AREA");
    }
    public BorderPane getPanel(){
        return rootPane;
    }
}
