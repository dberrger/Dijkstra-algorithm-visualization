package panels;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.ObservableModelImpl;

public class TextMessagePanel {
    private Pane textMessagePanel;
    private String message = "ZDAROVA";
    TextMessagePanel(){
        ObservableModelImpl.getInstance().registerTextPanel(this);
        textMessagePanel = new Pane();
        String style = "-fx-font: 18 arial; -fx-base: #FFFFFF; ";
        textMessagePanel.getChildren().add(new Label(message));
        textMessagePanel.setStyle(style);
    }
    public void setMessage(String s){
        this.message = s;
        this.update();
    }
    public void update(){
        textMessagePanel.getChildren().clear();
        textMessagePanel.getChildren().add(new Label(ObservableModelImpl.getInstance().getMessage()));
    }
    public Pane getPanel(){
        return textMessagePanel;
    }
}
