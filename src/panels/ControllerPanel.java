package panels;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class ControllerPanel {
    private FlowPane controllerPane;
    ControllerPanel(){
        controllerPane = new FlowPane(20,20);
        Button button = new Button("Test1");
        Button button1 = new Button("Test2");
        button.setMinSize(80,40);
        button1.setMinSize(80,40);
        String style = "-fx-font: 12 arial; -fx-base: #C0C0C0; ";
        button.setStyle(style);
        button1.setStyle(style);
        controllerPane.setOrientation(Orientation.VERTICAL);
        controllerPane.getChildren().addAll(button,button1);
    }
    public FlowPane getPanel(){
        return controllerPane;
    }
}
