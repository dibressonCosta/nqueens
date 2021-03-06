package queens;

public class SimulatedAnnealing extends NQueen{
	double tempreture;

    public SimulatedAnnealing(int boardSize, int tollerence, double tempreture) {
        super(boardSize, tollerence);
        this.tempreture = tempreture;
        currentState = new SimAnState(boardSize);
    }

    @Override
    public void solve() {
            double temperature;
            double delta;
            double probability;
            double rand;

            for (temperature = this.tempreture; (temperature > 0) && (currentState.getCost() != 0); temperature--) {
                nextState = currentState.getNextState();
                delta = currentState.getCost() - nextState.getCost();
                probability = Math.exp(delta / temperature);
                rand = Math.random();

                if (delta > 0) {
                    currentState = nextState;
                } else if (rand <= probability) {
                    currentState = nextState;
                }
            }
    }
}
