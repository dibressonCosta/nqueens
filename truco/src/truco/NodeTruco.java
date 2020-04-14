package truco;

public class NodeTruco extends Node{
	public NodeTruco(Node parent, State state) {
		super();
		this.parent = parent;
		this.state = state;
	}
	public int getScore() {
		return 1;
	}
}
