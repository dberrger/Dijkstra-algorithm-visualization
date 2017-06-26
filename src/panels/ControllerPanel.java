package panels;

import controller.MainController;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.impl.ObservableModelImpl;

import java.io.File;
import java.util.ArrayList;

public class ControllerPanel {
    //CONTROLLER PANEL VBox
    private VBox controllerPane;
    private MainController mainController;
    private FileChooser fileChooser;
    //STAGE FOR DIALOG SCREENS
    private Stage stage;
    private Boolean algorithmState;
    //ButtonListArrays
    private ArrayList<Button> algorithmButtons;
    private ArrayList<Button> notAlgorithmButtons;
    ControllerPanel(){

        //REGISTER TO OBSERVABLE MODEL IMPL WHEN ALGORITHM STATE CHANGE -> THIS GET NOTIFY TO UPDATE
        // -> THIS USE UPDATE METHOD -> GET NEW ALGORITHM STATE -> CHANGE BUTTON STATE -> VIEW CHANGE
        ObservableModelImpl.getInstance().registerControllerPanel(this);
        //INITIALIZING PRIVATE FIELD OBJECTS
        mainController= new MainController();
        controllerPane = new VBox();
        stage= new Stage();
        fileChooser = new FileChooser();
        algorithmButtons=new ArrayList<>();
        notAlgorithmButtons= new ArrayList<>();
        algorithmState=false;
        //VBOX BUTTONS GROUPS
        VBox ButtonGroup1 = new VBox(10);
        VBox ButtonGroup2 = new VBox(10);
        VBox ButtonGroup3 = new VBox(10);
        //LINES
        HBox Line1 = new HBox(10);
        HBox Line2 = new HBox(10);
        HBox Line3 = new HBox(10);
        HBox Line4 = new HBox(10);
        HBox Line5 = new HBox(10);
        HBox Line6 = new HBox(10);
        HBox line7 = new HBox(10);
        //LABELS
        Label EditGraphLabel = new Label("EditGraph: ");
        Label StepsLabel = new Label("Steps: ");
        Label OthersLabel = new Label("Others: ");
        //STYLE
        String style = "-fx-font: 12 arial; -fx-base: #a8afa4;  ";
        //BUTTONS
        Button AddEdgeButton = new Button("ADD EDGE");
        Button AddNodeButton = new Button("ADD NODE");
        Button DescriptionButton = new Button("DESCRIPTION");
        Button PrintShortestPaths = new Button("SHORTEST PATHS");
        Button EditEdgeButton = new Button("EDIT EDGE");  //удалить или изменить вес
        Button ExitButton = new Button("EXIT");
        Button ClearSceneButton = new Button("CLEAR SCENE");
        Button NextStepButton = new Button("NEXT STEP");
        Button PrevStepButton = new Button("PREV");
        Button SetStartPointButton = new Button("SET START");
        Button MoveToBeginButton = new Button("TO BEGIN");
        Button MoveToEndButton = new Button("TO END");
        Button ReadFromFileButton = new Button("READ FROM FILE");
        //ALGORITHM BUTTONS ARRAY LIST
        algorithmButtons.add(PrintShortestPaths);
        algorithmButtons.add(NextStepButton);
        algorithmButtons.add(PrevStepButton);
        algorithmButtons.add(MoveToBeginButton);
        algorithmButtons.add(MoveToEndButton);
        //NOT ALGORITHM BUTTONS ARRAY LIST
        notAlgorithmButtons.add(AddEdgeButton);
        notAlgorithmButtons.add(AddNodeButton);
        notAlgorithmButtons.add(EditEdgeButton);
        notAlgorithmButtons.add(DescriptionButton);
        notAlgorithmButtons.add(ExitButton);
        notAlgorithmButtons.add(ClearSceneButton);
        notAlgorithmButtons.add(SetStartPointButton);
        notAlgorithmButtons.add(ReadFromFileButton);

        ArrayList<Button> allButtons = new ArrayList<>();
        allButtons.addAll(algorithmButtons);
        allButtons.addAll(notAlgorithmButtons);

        for (Button b: allButtons){
            b.setMinSize(150,50);
            b.setStyle(style);
        }
        for (Button b: algorithmButtons){
            b.setDisable(true);
        }
        Line1.getChildren().addAll(AddNodeButton,SetStartPointButton);
        Line2.getChildren().addAll(AddEdgeButton,EditEdgeButton);
        Line3.getChildren().addAll(PrevStepButton,NextStepButton);
        Line4.getChildren().addAll(MoveToBeginButton,MoveToEndButton);
        Line5.getChildren().addAll(PrintShortestPaths,ClearSceneButton);
        Line6.getChildren().addAll(DescriptionButton,ReadFromFileButton);
        line7.getChildren().addAll(ExitButton);

        ButtonGroup1.getChildren().addAll(EditGraphLabel,Line1,Line2);
        ButtonGroup3.getChildren().addAll(StepsLabel,Line3,Line4);
        ButtonGroup3.getChildren().addAll(OthersLabel,Line5,Line6,line7);

        controllerPane.getChildren().addAll(ButtonGroup1,ButtonGroup2,ButtonGroup3);





        //ACTIONS OF BUTTONS
        AddNodeButton.setOnAction(e->mainController.addNode());

        AddEdgeButton.setOnAction(e-> AddingEdge());
        PrintShortestPaths.setOnAction(e-> ShortestPaths());
        EditEdgeButton.setOnAction(e-> EditEdge());

        NextStepButton.setOnAction(e->mainController.nextTurn());
        PrevStepButton.setOnAction(e->mainController.prevTurn());
        MoveToEndButton.setOnAction(e-> mainController.toLastStep());
        DescriptionButton.setOnAction(e-> ViewDescription());
        ClearSceneButton.setOnAction(e-> mainController.clearGraph());
        SetStartPointButton.setOnAction(e->startAlgorithm());
        ExitButton.setOnAction(e-> System.exit(0));
        MoveToBeginButton.setOnAction(e->mainController.toFirstStep());
        MoveToEndButton.setOnAction(e->mainController.toLastStep());
        ReadFromFileButton.setOnAction(e->readFromFile());

        changeButtonsState();
    }

    private void ViewDescription() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dijkstra Description");
        alert.setHeaderText("Dijkstra Algorithm Description");
        alert.setContentText(
                " Алгори́тм Де́йкстры (англ. Dijkstra's algorithm) — алгоритм на графах, \n" +
                        "изобретённый нидерландским учёным Эдсгером Дейкстрой в 1959 году.\n" +
                        " Находит кратчайшие пути от одной из вершин графа до всех остальных. \n" +
                        "Алгоритм работает только для графов без рёбер отрицательного веса");

        alert.showAndWait();
    }

    //READ FROM FILE
    private void readFromFile() {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            mainController.fileRead(file);
        }
    }

    //START ALGORITHM
    private void startAlgorithm(){
        Label NodeToEdit = new Label("INPUT START POINT");
        TextField fieldEdit = new TextField();
        Button buttonOK = new Button("OK");
        buttonOK.setPrefWidth(200);
        TextField fieldEdit2 = new TextField();

        fieldEdit.setPrefWidth(50);
        fieldEdit2.setPrefWidth(50);

        VBox root = new VBox();
        HBox hBox = new HBox();
        HBox hBox1 = new HBox();

        hBox.getChildren().addAll(NodeToEdit, fieldEdit);
        root.getChildren().addAll(hBox, hBox1, buttonOK);

        Scene secondScene = new Scene(root, 200,150);
        stage.setTitle("Start Algorithm");
        stage.setScene(secondScene);
        stage.show();
        buttonOK.setOnAction((ActionEvent e) ->{
            mainController.runAlgorithm(fieldEdit.getText());
            stage.close();
        });
    }
    //EDIT EDGE METHOD
    private void EditEdge(){

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
        stage.setTitle("EdgeEdit");
        stage.setScene(secondScene);
        stage.show();
    }
    //SHORTEST PATHS METHOD
    private void ShortestPaths(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ShortestPaths");
        alert.setHeaderText("ShortestPaths");
        alert.setContentText(ObservableModelImpl.getInstance().getShortestPaths());
        alert.showAndWait();
    }
    private void AddingEdge() {
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
        stage.setTitle("hui");
        stage.setScene(secondScene);
        stage.show();
        buttonOK.setOnAction(e->mainController.addEdge(pole1.getText(), pole2.getText(), pole3.getText()));

    }
    private void changeButtonsState(){
        if (this.algorithmState){
            unblockAlgorithmButtons();
        } else {
            blockAlgorithmButtons();
        }
    }
    private void blockAlgorithmButtons(){
        for (Button b: algorithmButtons){
            b.setDisable(true);
        }
    }
    private void unblockAlgorithmButtons(){
        for (Button b: algorithmButtons){
            b.setDisable(false);
        }
    }
    //RETURN THIS VBOX PANEL USAGE IN MAINPANEL
    public VBox getPanel(){
        return controllerPane;
    }

    public void update() {
        System.out.println("UPDATE COME HERE");
        this.algorithmState = ObservableModelImpl.getInstance().getAlgorithmState();
        System.out.println(this.algorithmState);
        changeButtonsState();
    }
}
