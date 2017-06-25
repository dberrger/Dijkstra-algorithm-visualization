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

import java.util.HashMap;
import java.util.Map;

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

        String style = "-fx-font: 12 arial; -fx-base: #a8afa4;  ";

        Button AddEdgeButton = new Button("ADD EDGE");
        Button AddNodeButton = new Button("ADD NODE");
        Button OpenFileButton = new Button("OPEN FILE");
        Button DeleteNodeButton = new Button("DELETE NODE");
        Button EditEdgeButton = new Button("EDIT EDGE");  //удалить или изменить вес
        Button ExitButton = new Button("EXIT");
        Button ClearSceneButton = new Button("CLEAR SCENE");
        Button NextStepButton = new Button("NEXT");
        Button PrevStepButton = new Button("PREV");
        Button SetStartPointButton = new Button("SET START");
        Button MoveToBeginButton = new Button("TO BEGIN");
        Button MoveToEndButton = new Button("TO END");

        Map<Integer, Button> ButtonMap = new HashMap<>();

        ButtonMap.put(0,AddEdgeButton);
        ButtonMap.put(1,AddNodeButton);
        ButtonMap.put(2,OpenFileButton);
        ButtonMap.put(3,DeleteNodeButton);
        ButtonMap.put(4, EditEdgeButton);
        ButtonMap.put(5,ExitButton);
        ButtonMap.put(6,ClearSceneButton);
        ButtonMap.put(7, NextStepButton);
        ButtonMap.put(8, PrevStepButton);
        ButtonMap.put(9, SetStartPointButton);
        ButtonMap.put(10, MoveToBeginButton);
        ButtonMap.put(11, MoveToEndButton);

        for(int i=0;i<12;i++){
            ButtonMap.get(i).setMinSize(150,50);
            ButtonMap.get(i).setStyle(style);
        }

        Line1.getChildren().addAll(AddNodeButton,DeleteNodeButton);
        Line2.getChildren().addAll(AddEdgeButton,EditEdgeButton);
        Line3.getChildren().addAll(PrevStepButton,NextStepButton);
        Line4.getChildren().addAll(MoveToBeginButton,MoveToEndButton);
        Line5.getChildren().addAll(SetStartPointButton,ClearSceneButton);
        Line6.getChildren().addAll(ExitButton,OpenFileButton);

        ButtonGroup1.getChildren().addAll(EditGraphLabel,Line1,Line2);
        ButtonGroup3.getChildren().addAll(StepsLabel,Line3,Line4);
        ButtonGroup3.getChildren().addAll(OthersLabel,Line5,Line6);

        controllerPane.getChildren().addAll(ButtonGroup1,ButtonGroup2,ButtonGroup3);






        AddNodeButton.setOnAction(e->mainController.addNode());
        AddEdgeButton.setOnAction(e-> AddingEdge());
        DeleteNodeButton.setOnAction(e-> DeleteNode());
        EditEdgeButton.setOnAction(e-> EditEdge());

        NextStepButton.setOnAction(e->mainController.nextTurn());
        PrevStepButton.setOnAction(e->mainController.prevTurn());
        //  MoveToBeginButton.setOnAction();
        MoveToEndButton.setOnAction(e-> mainController.runAlgorithm());

        // SetStartPointButton.setOnAction();
        ClearSceneButton.setOnAction(e-> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dijkstra Description");
            alert.setHeaderText("ЗДЕСЬ ОЧИСТИТСЯ СЦЕНА ГРАФА");
            alert.setContentText(
                    " Алгори́тм Де́йкстры (англ. Dijkstra's algorithm) — алгоритм на графах, \n" +
                            "изобретённый нидерландским учёным Эдсгером Дейкстрой в 1959 году.\n" +
                            " Находит кратчайшие пути от одной из вершин графа до всех остальных. \n" +
                            "Алгоритм работает только для графов без рёбер отрицательного веса");

            alert.showAndWait();



        });
        ExitButton.setOnAction(e-> System.exit(0));
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
