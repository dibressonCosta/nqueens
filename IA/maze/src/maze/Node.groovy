package maze

public abstract class Node {
	Node parent;
	public abstract Double h();
	public abstract Double g();
	public abstract Boolean isGoal();
	def expand () {};
}
