package panels;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.ObservableModelImpl;

public class TextMessagePanel {
    private Pane textMessagePanel;
    TextMessagePanel(){
        //REGISTER TO OBSERVABLE MODEL IMPL WHEN TEXT MESSAGE STATE CHANGE -> THIS GET NOTIFY TO UPDATE
        // -> THIS USE UPDATE METHOD -> GET NEW MESSAGE -> REPLACE -> VIEW CHANGE
        ObservableModelImpl.getInstance().registerTextPanel(this);
        textMessagePanel = new Pane();
        String style = "-fx-font: 18 arial; -fx-base: #FFFFFF; ";
        textMessagePanel.setStyle(style);
    }

    //UPDATE METHOD FOR OBSERVER
    public void update(){
        textMessagePanel.getChildren().clear();
        textMessagePanel.getChildren().add(new Label(ObservableModelImpl.getInstance().getMessage()));
    }
    //RETURN THIS PANE GRAPH PANE USAGE MAIN PANEL
    public Pane getPanel(){
        return textMessagePanel;
    }
}
