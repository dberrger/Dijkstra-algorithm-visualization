package panels;

import controller.MainController;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class ControllerPanel {
    private FlowPane controllerPane;
    private MainController mainController;
    ControllerPanel(){
        mainController= new MainController();
        controllerPane = new FlowPane(20,20);
        Button button = new Button("Test1");
        Button button1 = new Button("Test2");
        String style = "-fx-font: 12 arial; -fx-base: #C0C0C0; -fx-min-width: 80; -fx-min-height: 40;";
        button.setStyle(style);
        button1.setStyle(style);
        button.setOnAction(event -> mainController.addNode());
        button1.setOnAction(event -> mainController.deleteNode());
        controllerPane.setOrientation(Orientation.VERTICAL);
        controllerPane.getChildren().addAll(button,button1);
    }
    public FlowPane getPanel(){
        return controllerPane;
    }
}
