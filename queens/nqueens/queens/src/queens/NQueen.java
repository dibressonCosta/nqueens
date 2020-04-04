package queens;

abstract class NQueen {

    protected int boardSize;
    protected State currentState, nextState;
    protected int tollerenceCost;

    public NQueen(int boardSize, int tollrence) {
        this.boardSize = boardSize;
        this.tollerenceCost = tollrence;
        if (boardSize<4)
            throw new UnsupportedOperationException("No of N should be more than 3 to solve the problem");
    }

    abstract public void solve();

    public void show() {
        System.out.println("Total Cost of " + currentState.getCost());
        
        NodeQueen q[] = currentState.getQueens();
        System.out.println();

        int[][] tempBoard = new int[boardSize][boardSize];
		for (int i=0; i<boardSize; i++) {
			//Get the positions of Queen from the Present board and set those positions as 1 in temp board
			tempBoard[q[i].getIndexOfX()][q[i].getIndexOfY()]=1;
		}
		for (int i=0; i<boardSize; i++) {
			for (int j= 0; j < boardSize; j++) {
				System.out.print(tempBoard[i][j] + " ");
			}
			System.out.println();
		}
    }

    protected boolean isSolvedPossition(State s) {
        if (s.getCost() <= tollerenceCost) {
            return true;
        }
        return false;
    }
}

