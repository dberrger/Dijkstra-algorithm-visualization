package panels;

import javafx.scene.layout.Pane;
import model.ObservableModelImpl;

public class GraphPanel {
    private Pane graphPane;
    private String style = " -fx-border-radius: 7px;-fx-border-width: 2px;-fx-border-color: #434343; -fx-background-color: #edf7ff";
    GraphPanel(){
        ObservableModelImpl.getInstance().registerGraphPanel(this);
        graphPane = new Pane();
        graphPane.setCenterShape(true);
        graphPane.setMinSize(600,400);
        graphPane.setMaxSize(700,500);
        graphPane.setStyle(style);
    }

    public Pane getPanel(){
        return graphPane;
    }
    public void update(){
        graphPane.getChildren().clear();
        graphPane.getChildren().addAll(ObservableModelImpl.getInstance().getGraph().getChildren());
    }

}
