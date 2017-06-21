import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import panels.MainPanel;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        MainPanel mainPanel = new MainPanel();
        Scene scene = new Scene(mainPanel.getPanel(), 800, 600) ;
        primaryStage.setTitle("RABOTAEM") ;
        primaryStage.setScene(scene) ;
        primaryStage.show() ;
    }


    public static void main(String[] args) {
        launch(args);
    }

}