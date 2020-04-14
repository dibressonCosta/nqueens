package maze

class NodeMaze extends Node{
	int x;
	int y;
	Boolean close;
	Maze maze
	public NodeMaze(Node parent, int x, int y, Maze maze) {
		super();
		this.x = x
		this.y = y
		this.parent = parent
		this.close = false
		this.maze = maze
	}
	public NodeMaze(int x, int y, Maze maze) {
		super()
		this.x = x
		this.y = y
		this.close = false
		this.maze = maze
	}
	@Override
	public Boolean isGoal() {
		if(this.x == maze.getDestinationX() && this.y == maze.getDestinationY()) {
			return true
		}else
			return false
	}
	
	@Override
	public Double h() {
		Double dist = Math.sqrt((this.x - maze.getDestinationX())**2 + (this.y - maze.getDestinationY())**2  )
		return dist
	}

	@Override
	public Double g() {
		if(this.parent == null)
			return 0
		int g = this.parent.g() + 1
		return g;
	}
	public Double score() {
		return this.g() - this.h()
	}
	
}
