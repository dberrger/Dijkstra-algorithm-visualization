package panels;

import controller.MainController;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControllerPanel {
    private VBox controllerPane;
    private MainController mainController;
    ControllerPanel(){
        mainController= new MainController();
        controllerPane = new VBox();

        VBox ButtonGroup1 = new VBox(10);
        VBox ButtonGroup2 = new VBox(10);
        VBox ButtonGroup3 = new VBox(10);

        HBox Line1 = new HBox(10);
        HBox Line2 = new HBox(10);
        HBox Line3 = new HBox(10);
        HBox Line4 = new HBox(10);
        HBox Line5 = new HBox(10);
        HBox Line6 = new HBox(10);


        Label EditGraphLabel = new Label("EditGraph: ");
        Label StepsLabel = new Label("Steps: ");
        Label OthersLabel = new Label("Others: ");


        String style = "-fx-font: 12 arial; -fx-base: #C0C0C0; ";

        Button button0 = new Button("Add edge");
        Button button1 = new Button("Add node");
        Button button2 = new Button("Open ...");
        Button button3 = new Button("Delete node ...");
        Button button4 = new Button("Edit edge ...");  //удалить или изменить вес
        Button button5 = new Button("Exit ...");
        Button button6 = new Button("Description ");
        Button button7 = new Button("NEXT");
        Button button8 = new Button("RUN ALGORITHM");
        Button button9 = new Button("NEXT STEP");
        Button button10 = new Button("PREV STEP");
        Button button11 = new Button("TO END");

        //TODO POLNOSTIYU PEREDELAT VSE
        button0.setMinSize(150,50);
        button1.setMinSize(150,50);
        button2.setMinSize(150,50);
        button3.setMinSize(150,50);
        button4.setMinSize(150,50);
        button5.setMinSize(150,50);
        button6.setMinSize(150,50);
        button7.setMinSize(150,50);
        button8.setMinSize(150,50);
        button9.setMinSize(150,50);
        button10.setMinSize(150,50);
        button11.setMinSize(150,50);

        button0.setStyle(style);
        button1.setStyle(style);
        button2.setStyle(style);
        button3.setStyle(style);
        button4.setStyle(style);
        button5.setStyle(style);
        button6.setStyle(style);
        button7.setStyle(style);
        button8.setStyle(style);
        button9.setStyle(style);
        button10.setStyle(style);
        button11.setStyle(style);



        Line1.getChildren().addAll(button0,button1);
        Line2.getChildren().addAll(button2,button3);
        Line3.getChildren().addAll(button4,button5);
        Line4.getChildren().addAll(button6,button7);
        Line5.getChildren().addAll(button8,button9);
        Line6.getChildren().addAll(button10,button11);

        ButtonGroup1.getChildren().addAll(EditGraphLabel,Line1,Line2);
        ButtonGroup3.getChildren().addAll(StepsLabel,Line3,Line4);
        ButtonGroup3.getChildren().addAll(OthersLabel,Line5,Line6);



        button0.setOnAction(e-> AddingEdge());

        button5.setOnAction(e-> System.exit(0));
        button1.setOnAction(e->mainController.addNode());
        button6.setOnAction(E-> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dijkstra Description");
            alert.setHeaderText("32312312321321");
            alert.setContentText(" Алгори́тм Де́йкстры (англ. Dijkstra's algorithm) — алгоритм на графах, \n" +
                    "изобретённый нидерландским учёным Эдсгером Дейкстрой в 1959 году.\n" +
                    " Находит кратчайшие пути от одной из вершин графа до всех остальных. \n" +
                    "Алгоритм работает только для графов без рёбер отрицательного веса");

            alert.showAndWait();



        });
        button4.setOnAction(e-> EditEdge());

        button3.setOnAction(e-> DeleteNode());
        button8.setOnAction(e-> mainController.runAlgorithm());
        controllerPane.getChildren().addAll(ButtonGroup1,ButtonGroup2,ButtonGroup3);
    }

    public void EditEdge(){

        Label NodeToEdit = new Label("Node #1: ");
        Label NodeToEdit2 = new Label("Node #2: ");
        Button buttonOKEdit = new Button("OK");
        buttonOKEdit.setPrefWidth(200);
        TextField fieldEdit = new TextField();
        TextField fieldEdit2 = new TextField();

        fieldEdit.setPrefWidth(50);
        fieldEdit2.setPrefWidth(50);

        VBox root = new VBox();
        HBox hBox = new HBox();
        HBox hBox1 = new HBox();

        hBox.getChildren().addAll(NodeToEdit, fieldEdit);
        hBox1.getChildren().addAll(NodeToEdit2, fieldEdit2);
        root.getChildren().addAll(hBox, hBox1, buttonOKEdit);

        Scene secondScene = new Scene(root, 200,150);
        Stage secondStage = new Stage();
        secondStage.setTitle("EdgeEdit");
        secondStage.setScene(secondScene);
        secondStage.show();
    }

    public void DeleteNode(){

        Label NodeToDelete = new Label("Enter Node to DELETE: ");
        Button buttonOKDelete = new Button("OK");
        buttonOKDelete.setPrefWidth(200);
        TextField fieldDelete = new TextField();

        fieldDelete.setPrefWidth(90);
        VBox root = new VBox();
        HBox hBoxDelete = new HBox();

        hBoxDelete.getChildren().addAll(fieldDelete, buttonOKDelete);
        root.getChildren().addAll(NodeToDelete, hBoxDelete);

        Scene secondScene = new Scene(root, 200,150);
        Stage secondStage = new Stage();
        secondStage.setTitle("NodeDELETE");
        secondStage.setScene(secondScene);
        secondStage.show();

    }

    public void AddingEdge() {
        Label Node1 = new Label("Node #1: ");
        Label Node2 = new Label("Node #2: ");
        Label EdgeWeight = new Label("EWeight:  ");
        Button buttonOK = new Button("OK");
        buttonOK.setPrefWidth(200);
        TextField pole1 = new TextField();
        TextField pole2 = new TextField();
        TextField pole3 = new TextField();
        pole1.setPrefWidth(50);
        pole2.setPrefWidth(50);
        pole3.setPrefWidth(50);
        VBox root = new VBox();
        HBox hBox = new HBox();
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();

        hBox.getChildren().addAll(Node1, pole1);
        hBox1.getChildren().addAll(Node2, pole2);
        hBox2.getChildren().addAll(EdgeWeight, pole3);
        root.getChildren().addAll(hBox, hBox1, hBox2, buttonOK);

        Scene secondScene = new Scene(root, 200,150);
        Stage secondStage = new Stage();
        secondStage.setTitle("hui");
        secondStage.setScene(secondScene);
        secondStage.show();
        buttonOK.setOnAction(e->mainController.addEdge(Integer.parseInt(pole1.getText()), Integer.parseInt(pole2.getText()), Integer.parseInt(pole3.getText())));

    }
    public VBox getPanel(){
        return controllerPane;
    }
}
