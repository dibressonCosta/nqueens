package truco;

import java.util.Scanner;

public class Search {
	Player p1, p2;
	State state;
	Deck deck;
	private Scanner scanner;
	boolean startPlayer = true; // true para humano começando e false para o frank começando

	public Search(Player p1, Player p2, State state) {
		this.p1 = p1;
		this.p2 = p2;
		this.deck = this.p1.deck;
		this.state = state;
	}

	public Card BestPlay(Player p2, int round) {
		Double bestScore = Double.NEGATIVE_INFINITY;
		Card best = null;
		State statetemp = new State(state, round);
		for (Card carta : p2.hand) {
			statetemp.playsp2.add(carta);
			if (statetemp.getScore() > bestScore) {
				bestScore = statetemp.getScore();
				best = carta;

			}
			statetemp.playsp2.remove(carta);
		}
		p2.hand.remove(best);
		return best;
	}

	public boolean atualizaScore(int round) {
		boolean test1 = state.playsp1.get(round).isManilha(state.manilha);
		boolean test2 = state.playsp2.get(round).isManilha(state.manilha);
		if (test1 && test2) {
			if (state.playsp1.get(round).naipe.compareTo(state.playsp2.get(round).naipe) == 1) {
				state.p1.score++;
			} else {
				state.p2.score++;
			}
		} else {
			if (state.playsp1.get(round).value > state.playsp2.get(round).value) {
				state.p1.score++;
				startPlayer = true;
			}
			if (state.playsp1.get(round).value < state.playsp2.get(round).value) {
				state.p2.score++;
				startPlayer = false;
			}
		}
		if (state.p1.score == 2 || state.p2.score == 2) {
			return true;
		} else
			return false;
	}

	public void getWinner() {
		if (state.p1.score == 2) {
			System.out.println("O vencedor foi você!!");
		} else
			System.out.println("O vencedor foi o Frank!!");
	}

	public void play() {
		int round = 0;
		boolean cond = false;
		scanner = new Scanner(System.in);
		state.printState();
		while (!cond) {
			System.out.println();
			// if (startPlayer) {
			System.out.println("Escolha a carta para jogar, usando 1 para a primeira e 3 para última: ");
			int cartap1 = scanner.nextInt() - 1;
			state.playsp1.add(p1.hand.get(cartap1));
			System.out.println();
			System.out.println("A carta que você escolheu foi: " + p1.hand.remove(cartap1).rep);
			Card cartinha = BestPlay(p2, round);
			state.playsp2.add(cartinha);
			System.out.println("A carta que o pc escolheu foi: " + cartinha.rep);

//			if(!startPlayer) {
//				Card cartinha = BestPlay(p2, round);
//				state.playsp2.add(cartinha);
//				System.out.println("A carta que o pc escolheu foi: " + cartinha.rep);
//				System.out.println();
//				System.out.println("Escolha a carta para jogar, usando 1 para a primeira e 3 para última: ");
//				int cartap1 = scanner.nextInt() - 1;
//				state.playsp1.add(p1.hand.get(cartap1));
//				System.out.println();
//				System.out.println("A carta que você escolheu foi: " + p1.hand.remove(cartap1).rep);
//			}
			cond = atualizaScore(round);
			round++;
		}
		getWinner();
		System.out.println("Jogadas do jogador 1:" + state.playsp1);
		System.out.println("Jogadas do jogador 2:" + state.playsp2);

	}
}
