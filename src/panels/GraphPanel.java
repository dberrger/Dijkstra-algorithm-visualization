package panels;

import javafx.scene.layout.Pane;
import model.impl.ObservableModelImpl;

public class GraphPanel {
    private Pane graphPane;

    GraphPanel(){
        //REGISTER TO OBSERVABLE MODEL IMPL WHEN GRAPH PANE STATE CHANGE -> THIS GET NOTIFY TO UPDATE
        // -> THIS USE UPDATE METHOD -> GET NEW GRAPH PANE -> REPLACE -> VIEW CHANGE
        ObservableModelImpl.getInstance().registerGraphPanel(this);
        graphPane = new Pane();
        //SET STYLES
        graphPane.setCenterShape(true);
        graphPane.setMinSize(600,400);
        graphPane.setMaxSize(700,500);
        String style = " -fx-border-radius: 7px;-fx-border-width: 2px;-fx-border-color: #434343; -fx-background-color: #edf7ff";
        graphPane.setStyle(style);
    }
    //RETURN THIS PANE GRAPH PANE USAGE MAIN PANEL
    public Pane getPanel(){
        return graphPane;
    }
    //UPDATE METHOD FOR OBSERVER
    public void update(){
        graphPane.getChildren().clear();
        graphPane.getChildren().addAll(ObservableModelImpl.getInstance().getGraph().getChildren());
    }

}
