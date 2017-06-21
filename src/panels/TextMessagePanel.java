package panels;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class TextMessagePanel {
    private Pane textMessagePanel;
    private String message = "ZDAROVA";
    TextMessagePanel(){
        textMessagePanel = new Pane();
        String style = "-fx-font: 18 arial; -fx-base: #FFFFFF; ";
        textMessagePanel.getChildren().add(new Label(message));
        textMessagePanel.setStyle(style);
    }
    public void setMessage(String s){
        this.message = s;
        this.update();
    }
    private void update(){
        textMessagePanel.getChildren().clear();
        textMessagePanel.getChildren().add(new Label(message));
    }
    public Pane getPanel(){
        return textMessagePanel;
    }
}
