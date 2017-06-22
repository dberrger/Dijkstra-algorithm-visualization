package structures;

public class Edge {
    public Node first;
    public Node second;
    public int weight;
    public boolean color;

    Edge(Node first, Node second, int weight){
        this.first=first;
        this.second=second;
        this.weight=weight;
    }
}
