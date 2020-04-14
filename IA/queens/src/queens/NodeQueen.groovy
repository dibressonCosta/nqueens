package queens

class NodeQueen extends Node{

	private int row;
    private int col;
	public NodeQueen(int row, int col) {
		super();
		this.row = row
		this.col = col
	}
	public move() {
		row++
	}
	public Boolean isAttacked(NodeQueen queen) {
		if(row == queen.row || col == queen.col)
			return true;
			//  Check diagonals
		else if(Math.abs(col-queen.col) == Math.abs(row-queen.row))
			return true;
		return false;
	}
	public int getIndexOfX() {
		return row
	}

	public int getIndexOfY() {
		return col
	}
	@Override
	public Double h() {
		return 0
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
