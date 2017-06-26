package model.impl;

import javafx.scene.layout.Pane;
import model.ObservableModel;
import panels.ControllerPanel;
import panels.GraphPanel;
import panels.TextMessagePanel;

public class ObservableModelImpl implements ObservableModel {
    //GRAPH PANE FOR GRAPH PANE TO SET
    private Pane graph;
    //MESSAGE FOR TEXT MESSAGE PANE TO SET
    private String message;
    //SHORTEST PATHS STRING
    private String shortestPaths;
    private Boolean algorithmState;
    //PANELS TO REGISTER
    private GraphPanel graphPane;
    private TextMessagePanel textMessagePane;
    private ControllerPanel controllerPanel;

    private static ObservableModelImpl instance;
    //PRIVATE SINGLETON CONSTRUCTOR
    private ObservableModelImpl() {
        //INITIALIZATION;
        algorithmState =false;
        graph= new Pane();
        message="";
        shortestPaths="Run Algorithm First";
    }
    //STATIC GET INSTANCE METHOD WITH LAZY INITIALIZATION
    public static ObservableModelImpl getInstance() {
        ObservableModelImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (ObservableModelImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ObservableModelImpl();
                }
            }
        }
        return localInstance;
    }
    //SET GRAPH  -> THEN NOTIFY GRAPH PANEL TO GET THIS PANEL
    public void setGraph(Pane graph){
        this.graph=graph;
        notifyGraphPanel();
    }
    //SET MESSAGE -> THEN NOTIFY TEXT MESSAGE PANEL TO GET THIS MESSAGE
    public void setMessage(String message){
        this.message=message;
        notifyTextMessagePanel();
    }
    //METHOD TO SET SHORTEST PATHS STRING (USAGE IN DIJKSTRA ALGORITHM MODEL TO SET SHORTEST PATHS AFTER RUNNING ALGORITHM
    public void setShortestPaths(String shortestPaths){
        this.shortestPaths=shortestPaths;
    }
    //METHOD TO GET SHORTEST PATHS STRING(USAGE IN CONTROLLER PANEL TO GET SHORTEST PATHS TO SCREEN
    public String getShortestPaths(){
        return this.shortestPaths;
    }

    //GET GRAPH TO GRAPH PANEL USAGE GRAPH PANEL(OBSERVER)
    public Pane getGraph(){
        return graph;
    }
    //GET MESSAGE TO TEXT MESSAGE PANEL USAGE TEXT MESSAGE PANEL PANEL(OBSERVER)
    public String getMessage(){
        return message;
    }
    //GET ALGORITHM STATE
    public Boolean getAlgorithmState() {
        return algorithmState;
    }
    //SET ALGORITHM STATE
    public void setAlgorithmState(Boolean algorithmState) {
        this.algorithmState = algorithmState;
        notifyControllerPanel();
    }
    //REGISTER TEXT PANEL
    @Override
    public void registerTextPanel(TextMessagePanel textMessagePanel) {
        this.textMessagePane=textMessagePanel;
    }
    //REGISTER GRAPH PANEL
    @Override
    public void registerGraphPanel(GraphPanel graphPanel) {
        this.graphPane=graphPanel;
    }

    @Override
    public void registerControllerPanel(ControllerPanel controllerPanel) {
        this.controllerPanel=controllerPanel;
    }

    //NOTIFY TEXT MESSAGE PANEL (WHEN MESSAGE CHANGES -> textMessagePanel use getMessage of this class -> View Change)
    @Override
    public void notifyTextMessagePanel() {
        this.textMessagePane.update();
    }
    //NOTIFY GRAPH PANEL (WHEN GRAPH PANE CHANGES -> GRAPH PANEL ue getGraphPane Method of this class -> View Change)
    @Override
    public void notifyGraphPanel() {
        this.graphPane.update();
    }

    @Override
    public void notifyControllerPanel() {
        this.controllerPanel.update();
    }

}
