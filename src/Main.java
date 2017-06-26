import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import panels.MainPanel;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        MainPanel mainPanel = new MainPanel();
        Scene scene = new Scene(mainPanel.getPanel(), 950, 650) ;
        primaryStage.setTitle("DIJKSTRA ALGORITHM") ;
        primaryStage.setScene(scene) ;
        primaryStage.show() ;
    }


    public static void main(String[] args) {
        launch(args);
    }

}