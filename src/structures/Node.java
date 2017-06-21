package structures;

import java.util.ArrayList;

public class Node {
    public int x;
    public int y;
    public int index;
    public int dist;
    public boolean out;
    public boolean in;
    public ArrayList<Integer> prev;
    Node(int x, int y, int index){
        this.x=x;
        this.y=y;
        this.index=index;
    }
}
