package queens
public abstract class Node {

	Node parent;
	public abstract Double h();
	public abstract Double g();
	def expand () {};

}

