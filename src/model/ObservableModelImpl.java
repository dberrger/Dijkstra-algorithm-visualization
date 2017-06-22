package model;

import javafx.scene.layout.Pane;
import panels.GraphPanel;
import panels.TextMessagePanel;

public class ObservableModelImpl implements ObservableModel {
    private Pane graph;
    private String message;
    private GraphPanel graphPane;
    private TextMessagePanel textMessagePane;


    private static ObservableModelImpl instance;

    private ObservableModelImpl() {
        graph= new Pane();
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
    public Pane getGraph(){
        return graph;
    }
    public String getMessage(){
        return message;
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
