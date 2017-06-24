package controller;

import model.MainModel;
import model.MainModelImpl;

public class MainController {
    private MainModel mainModel;
    public MainController(){
        mainModel = new MainModelImpl();
    }
    public void addNode(){
        mainModel.addNode();
    }
    public void addEdge(int first, int second, int weight){
        mainModel.addEdge(first,second,weight);
    }
    public void deleteNode(){
        mainModel.onPrevTurn();
        System.out.println("DELETENODE");
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
