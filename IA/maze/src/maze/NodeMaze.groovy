package maze

class NodeMaze extends Node{
	int x;
	int y;
	Boolean close;
	public NodeMaze(Node parent, int x, int y) {
		super();
		this.x = x
		this.y = y
		this.parent = parent
		this.close = false
	}
	public NodeMaze(int x, int y) {
		super()
		this.x = x
		this.y = y
		this.close = false
	}
	
	@Override
	public Boolean isGoal() {
		if(this.x == 31 && this.y == 15) {
			return true
		}else
			return false
	}
	
	@Override
	public Double h() {
		
		return null;
	}

	@Override
	public Double g() {
		
		return null;
	}
	
}
