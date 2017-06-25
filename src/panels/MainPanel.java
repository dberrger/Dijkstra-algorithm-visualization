package panels;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;


public class MainPanel {
    private BorderPane rootPane;
    public MainPanel(){
        rootPane= new BorderPane();
        //INITIALIZE PANELS
        GraphPanel graphPanel = new GraphPanel();
        ControllerPanel controllerPanel = new ControllerPanel();
        TextMessagePanel textMessagePanel = new TextMessagePanel();
        //GET PRIVATE PANELS FIELDS OF PANELS OBJECTS AND POSITION THEM ON SCREEN
        rootPane.setLeft(graphPanel.getPanel());
        rootPane.setRight(controllerPanel.getPanel());
        rootPane.setBottom(textMessagePanel.getPanel());
        //STYLES
        rootPane.setMargin(controllerPanel.getPanel(),new Insets(50.0,100.0,0.0,15.0));
        rootPane.setMargin(rootPane.getBottom(),new Insets(50.0,0.0,50.0,100.0));
        rootPane.setPadding(new Insets(10,10,10,10));
    }
    //GET THIS BORDER PANE USAGE MAIN FUNCTION
    public BorderPane getPanel(){
        return rootPane;
    }
}
