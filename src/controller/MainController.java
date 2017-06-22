package controller;

import model.MainModel;
import model.MainModelImpl;

public class MainController {
    private MainModel mainModel;
    public MainController(){
        mainModel = new MainModelImpl();
    }
    public void addNode(){
        mainModel.onNextTurn();
        System.out.println("ADD NODE");
    }
    public void deleteNode(){
        mainModel.onPrevTurn();
        System.out.println("DELETENODE");
    }
    public void nextStep(){
        System.out.println("NEXT STEP");
    }
    public void prevStep(){
        System.out.println("PREV STEP");
    }
}
