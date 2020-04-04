package queens;

import java.util.Random;


public class SimAnState extends State{
	Random randomGenerator = new Random();

    public SimAnState(int boardSize) {
        super(boardSize);
        for (int i = 0; i < boardSize; i++) {
            q[i] = new NodeQueen(i, randomGenerator.nextInt(boardSize));
        }
    }

    public SimAnState(int boardSize, NodeQueen q[]) {
        super(boardSize);
        this.q = q;
        cost = 0;
    }


    @Override
    public State getNextState() {
        int i;
        NodeQueen nextStateQueen[] = new NodeQueen[boardSize];
        //pick a random queen to change at a time
        int rand = randomGenerator.nextInt(boardSize);

        for (i = 0; i < boardSize; i++) {
            nextStateQueen[i] = new NodeQueen(q[i].getIndexOfX(), q[i].getIndexOfY());
            //we only change that randomly picked queen in this state! 
            //if the current queen is the queen we picked the next state will be the same
            if (rand == i) {
                int temp = randomGenerator.nextInt(boardSize);
                //this is to ensure that the new state will be different of the previous state
                while (temp == q[i].getIndexOfY()) {
                    temp = randomGenerator.nextInt(boardSize);
                }
                //new state will be added to the new state
                nextStateQueen[i] = new NodeQueen(q[i].getIndexOfX(), temp);
            }
        }

        return new SimAnState(boardSize, nextStateQueen);
    }
}
