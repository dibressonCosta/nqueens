package queens
import groovy.time.TimeCategory
import groovy.time.TimeDuration

class HillClimbing {
	private static int n ;
	private static int stepsClimbedAfterLastRestart = 0;
	private static int stepsClimbed =0;
	private static int heuristic = 0;
	private static int randomRestarts = 0;
	
	public HillClimbing(int n) {
		this.n = n
	}
	
	//Method to create a new random board with n queens
	public static State generateState() {
		State startState = new HillState(n);
		Random r = new Random();
		for(int i=0; i<n; i++){
			startState.q[i] = new NodeQueen(r.nextInt(n), i);
		}
		return startState;
	}

	//Method to print the Current State1
	public static void printState (HillState state) {
		//Creating temporary board from the present board
		int[][] tempBoard = new int[n][n];
		for (int i=0; i<n; i++) {
			//Get the positions of Queen from the Present board and set those positions as 1 in temp board
			tempBoard[state.q[i].row][state.q[i].col]=1;
		}
		println ""
		for (int i=0; i<n; i++) {
			for (int j= 0; j < n; j++) {
				System.out.print(tempBoard[i][j] + " ");
			}
			System.out.println();
		}
	}

	// Method to find Heuristics of a state
	public static int findHeuristic (HillState state) {
		int heuristic = 0;
		for (int i = 0; i< state.q.length; i++) {
			for (int j=i+1; j<state.q.length; j++ ) {
				if (state.q[i].isAttacked(state.q[j])) {
					heuristic++;
				}
			}
		}
		return heuristic;
	}

	// Method to get the next state with lower heuristic
	public static HillState nextState (HillState presentState) {
		HillState nextState = new HillState(n);
		HillState tmpState = new HillState(n);
		int presentHeuristic = findHeuristic(presentState);
		int bestHeuristic = presentHeuristic;
		int tempH;

		for (int i=0; i<n; i++) {
			//Copy present state as best state and temp state
			nextState.q[i] = new NodeQueen(presentState.q[i].row, presentState.q[i].col);
			tmpState.q[i] = nextState.q[i];
		}
		//  Iterate each column
		for (int i=0; i<n; i++) {
			if (i>0)
				tmpState.q[i-1] = new NodeQueen(presentState.q[i-1].row, presentState.q[i-1].col);
			tmpState.q[i] = new NodeQueen (0, tmpState.q[i].col);
			//  Iterate each row
			for (int j=0; j<n; j++) {
				//Get the heuristic
				tempH = findHeuristic(tmpState);
				//Check if temp board better than best board
				if (tempH < bestHeuristic) {
					bestHeuristic = tempH;
					//  Copy the temp board as best board
					for (int k=0; k<n; k++) {
						nextState.q[k] = new NodeQueen(tmpState.q[k].row, tmpState.q[k].col);
					}
				}
				//Move the queen
				if (tmpState.q[i].row!=n-1)
					tmpState.q[i].move();
			}
		}
		//Check whether the present state and the best state found have same heuristic
		//Then randomly generate a new state and assign it to best state
		if (bestHeuristic == presentHeuristic) {
			randomRestarts++;
			stepsClimbedAfterLastRestart = 0;
			nextState = generateState();
			heuristic = findHeuristic(nextState);
		} else
			heuristic = bestHeuristic;
		stepsClimbed++;
		stepsClimbedAfterLastRestart++;
		return nextState;
	}
	
	public static void solveHill() {
		int presentHeuristic
		System.out.println("Solution to "+n+" queens using hill climbing with random restart:");
		//Creating the initial State1
		State presentState = generateState();
		Date start = new Date()
		presentHeuristic = findHeuristic(presentState);
		
		// test if the present State1 is the solution State1
		while (presentHeuristic != 0) {
			//  Get the next state
			//printState(presentState);
			presentState = nextState(presentState);
			presentHeuristic  = heuristic;
		}
		Date end = new Date()
		TimeDuration td = TimeCategory.minus( end, start )
		//Printing the solution
		printState(presentState);
		System.out.println("\nTotal number of Steps Climbed: " + stepsClimbed);
		System.out.println("Number of random restarts: " + randomRestarts);
		System.out.println("Steps Climbed after last restart: " + stepsClimbedAfterLastRestart);
		println "Time: "+td
	}
}
