package controller;

import model.DijkstraModel;
import model.DrawModel;

public class MainController {
    public void addNode(){
        System.out.println("ADD NODE");
        DrawModel.getInstance().drawGraph();
    }
    public void deleteNode(){
        System.out.println("DELETENODE");
    }
    public void nextStep(){
        System.out.println("NEXT STEP");
    }
    public void prevStep(){
        System.out.println("PREV STEP");
    }
}
