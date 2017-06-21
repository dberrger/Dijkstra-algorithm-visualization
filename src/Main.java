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


public class Main extends Application {
    Pane pane;
    @Override
    public void start(Stage primaryStage) throws Exception{
        pane = new Pane();
        pane.setCenterShape(true);
        pane.setMaxHeight(400);
        pane.setMaxWidth(400);
        pane.setLayoutX(50);
        pane.setLayoutY(50);
        getNodesXY(10);
        BorderPane rootPane = new BorderPane();
        Group root = new Group(pane) ;
        Button button = new Button("EBAT");
        Button button1 = new Button("EBANII V ROT");
        button.setMinSize(80,40);
        button1.setMinSize(80,40);
        String style = "-fx-font: 12 arial; -fx-base: #C0C0C0; ";
        button.setStyle(style);
        button1.setStyle(style);
        FlowPane buttonPane = new FlowPane(20,20);
        buttonPane.setOrientation(Orientation.VERTICAL);
        buttonPane.getChildren().addAll(button,button1);
        rootPane.setLeft(root);
        rootPane.setRight(buttonPane);
        rootPane.setMargin(buttonPane,new Insets(50.0,200.0,0.0,0.0));
        rootPane.setMargin(root,new Insets(20.0, 0.0, 0.0, 20.0));

        Scene scene = new Scene(rootPane, 800, 600) ;

        primaryStage.setTitle("RABOTAEM") ;

        primaryStage.setScene(scene) ;

        primaryStage.show() ;
    }


    public static void main(String[] args) {
        launch(args);
    }
    public void getNodesXY(int nodeCount){
        for (int i=0;i<nodeCount;i++){
            float angle = (float) (2.0 * 3.1415926 * i / nodeCount);
            float dx = (float) (200 * Math.cos(angle));
            float dy = (float) (200 * Math.sin(angle));
            System.out.println("I " + i + "DX " + dx + " DY " + dy);
            drawCircle(dx,dy);
        }

    }
    public void drawCircle(float x, float y){
        Circle c = new Circle(x,y,20);
        c.fillProperty();
        pane.getChildren().addAll(c);
    }
}