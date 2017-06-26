package controller;

import model.MainModel;
import model.impl.MainModelImpl;
import model.impl.ObservableModelImpl;

import java.io.File;

public class MainController {
    //TODO DELETE THIS IN LAST VARIANT
    private MainModel mainModel;
    public MainController(){
        mainModel = new MainModelImpl();
    }
    public void addNode(){
        mainModel.addNode();
    }
    public void addEdge(String first, String second, String weight){
        try {
            mainModel.addEdge(Integer.parseInt(first), Integer.parseInt(second), Integer.parseInt(weight));
        }
        catch (Exception e ){
            ObservableModelImpl.getInstance().setMessage("ERROR WHILE ADDING NEW EDGE \n INPUT CORRECT PARAMETERS");
        }
    }
    public void runAlgorithm(String s)
    {
        try {
            Integer.parseInt(s);
        }
        catch (Exception e ){
            ObservableModelImpl.getInstance().setMessage("ERROR WHILE PARSE START POINT \n INPUT COORECT PARAMETERS");
            return;
        }
        mainModel.runAlgorithm(Integer.parseInt(s));
    }
    public void fileRead(File file){
        if (file!=null) {
            mainModel.readGraphFromFile(file);
        }

    }
    public void nextTurn(){
        mainModel.onNextTurn();
    }
    public void prevTurn(){
        mainModel.onPrevTurn();
    }

    public void toLastStep() {
        mainModel.toLastStep();
    }
    public void toFirstStep(){
        mainModel.toFirstStep();
    }

    public void clearGraph() {
        mainModel.clearGraph();
    }
    public void deleteEdge(String first, String second){
        try {
            mainModel.deleteEdge(Integer.parseInt(first), Integer.parseInt(second));
        }
        catch (Exception e ){
            ObservableModelImpl.getInstance().setMessage("ERROR WHILE DELETING EDGE \n INPUT CORRECT PARAMETERS");
        }
    }
}
