package Maze;

public abstract class Node {
    Node parent;
    public abstract Double h();
    public abstract Double g();
    public abstract Boolean isGoal();
    Collection<Node> expand;
    
}
