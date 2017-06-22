package model;

import javafx.scene.layout.Pane;
import panels.GraphPanel;
import panels.TextMessagePanel;
import structures.Graph;

import java.util.ArrayList;

public class ObservableModelImpl implements ObservableModel {
    private Pane graph;
    private String message;
    private GraphPanel graphPane;
    private TextMessagePanel textMessagePane;
    private int currentTurn;
    private ArrayList<Graph> turns;


    private static ObservableModelImpl instance;

    private ObservableModelImpl() {
        graph= new Pane();
        currentTurn=0;
        turns=new ArrayList<>();
        message="Deijkstra Algorithm Visualization";
    }
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
    public void setGraph(Pane graph){
        this.graph=graph;
        notifyGraphPanel();
    }
    public void setMessage(String message){
        this.message=message;
        notifyTextMessagePanel();
    }
    public void setTurns(ArrayList<Graph> turns){
        this.turns= turns;
    }
    public Pane getGraph(){
        return graph;
    }
    public String getMessage(){
        return message;
    }
    public Graph getCurrentTurnGraph(){
        return turns.get(currentTurn);
    }
    public Graph getInitialGraph(){
        return turns.get(0);
    }
    @Override
    public void registerTextPanel(TextMessagePanel textMessagePanel) {
        this.textMessagePane=textMessagePanel;
    }

    @Override
    public void registerGraphPanel(GraphPanel graphPanel) {
        this.graphPane=graphPanel;
    }

    @Override
    public void notifyTextMessagePanel() {
        this.textMessagePane.update();
    }

    @Override
    public void notifyGraphPanel() {
        this.graphPane.update();
    }

}
