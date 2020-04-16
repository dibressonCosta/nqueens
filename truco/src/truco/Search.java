package truco;

import java.util.Scanner;

/** Frank its the IA name that will play truco **/

public class Search {
	State state;
	Deck deck;
	private Scanner scanner;
	boolean startPlayer = true; // true for human starting and false for frank

	public Search(State state) {
		this.deck = state.deck;
		this.state = state;
	}

	public State minimax(State state, int round, boolean isMaximizing) {
		State bestState = state;
		if (round > 2) {
			return bestState;
		}
		if (state.checkWin() != 0) {
			return bestState;
		}
		if (isMaximizing) {
			Double bestScore = Double.NEGATIVE_INFINITY;
			for (Card carta : state.p2.hand) {
				state.playsp2.add(carta);
				state.atualizaScore();
				State temp = minimax(state, round + 1, false);
				Double score = temp.getScore();
				state.playsp2.remove(carta);
				if (score > bestScore) {
					bestScore = score;
					bestState = temp;
				}
			}
			return bestState;
		} else {
			Double bestScore = Double.POSITIVE_INFINITY;
			for (Card carta : state.p1.hand) {
				state.playsp1.add(carta);
				state.atualizaScore();
				State temp = minimax(state, round + 1, true);
				Double score = temp.getScore();
				state.playsp1.remove(carta);
				if (score < bestScore) {
					bestState = temp;
					bestScore = score;
				}
			}
			return bestState;
		}
	}

	public Card BestPlay(int round, boolean startPlayer) {
		Double bestScore = Double.NEGATIVE_INFINITY;
		Card best = null;
		State stateTemp = null;
		if (!startPlayer) {
			for (Card carta : state.p1.hand) {
				state.playsp1.add(carta);
				Card bestTemp = BestPlay(round, !startPlayer);
				stateTemp = minimax(state, round, !startPlayer);
				if (stateTemp.getScore() > bestScore) {
					bestScore = stateTemp.getScore();
					best = bestTemp;
				}
				state.playsp1.remove(carta);
			}
			state.p2.hand.remove(best);
			return best;

		}
		for (Card carta : state.p2.hand) {
			state.playsp2.add(carta);
			stateTemp = minimax(state, round, !startPlayer);
			if (stateTemp.getScore() > bestScore) {
				bestScore = stateTemp.getScore();
				best = carta;
			}
			state.playsp2.remove(carta);
		}
		state.p2.hand.remove(best);
		return best;
	}

	public void play() {
		scanner = new Scanner(System.in);
		state.printState();
		boolean startPlayer = true;
		while (state.checkWin() == 0) {
			System.out.println();
			if (state.startHuman) {
				state.printHandP1();
				System.out.println();
				System.out.println("Escolha sua carta para jogar, usando 1 para a primeira e 3 para última: ");
				int cartap1 = scanner.nextInt() - 1;
				state.playsp1.add(state.p1.hand.get(cartap1));
				System.out.println();
				System.out.println("A carta que você escolheu foi: " + state.p1.hand.remove(cartap1).rep);
				Card cartinha = BestPlay(state.round, startPlayer);
				state.playsp2.add(cartinha);
				System.out.println("A carta que o Frank escolheu foi: " + cartinha.rep);
			}else if (!state.startHuman) {
				Card cartinha = BestPlay(state.round, startPlayer);
				state.playsp2.add(cartinha);
				System.out.println("A carta que o Frank escolheu foi: " + cartinha.rep);
				System.out.println();
				state.printHandP1();
				System.out.println();
				System.out.println("Escolha a carta para jogar, usando 1 para a primeira e 3 para última: ");
				int cartap1 = scanner.nextInt() - 1;
				state.playsp1.add(state.p1.hand.get(cartap1));
				System.out.println();
				System.out.println("A carta que você escolheu foi: " + state.p1.hand.remove(cartap1).rep);
			}
			state.atualizaScore();
			state.round++;
		}
		if (state.checkWin() == 1)
			System.out.println("O vencedor foi o Frank!!");
		else
			System.out.println("O vencedor foi você");
	}
}
