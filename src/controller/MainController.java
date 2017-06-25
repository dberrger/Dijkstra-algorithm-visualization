package controller;

import model.MainModel;
import model.MainModelImpl;
import model.ObservableModelImpl;

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
    public void runAlgorithm(){
        mainModel.runAlgorithm();
    }
    public void nextTurn(){
        mainModel.onNextTurn();
    }
    public void prevTurn(){
        mainModel.onPrevTurn();
    }
}
