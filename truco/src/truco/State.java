package truco;

import java.util.ArrayList;
import java.util.List;

public class State {
	Player p1, p2;
	Deck deck;
	Card vira;
	Card manilha;
	int round;
	boolean startHuman = true; // if true, human play first
	boolean fezPrimeira = false; // if true, human made the first point
	List<Card> playsp1 = new ArrayList<Card>();
	List<Card> playsp2 = new ArrayList<Card>();

	public State(Player p1, Player p2, Deck deck, int round) {
		this.p1 = p1;
		this.p2 = p2;
		this.deck = deck;
		this.vira = deck.shuffleCard();
		this.round = round;
		if (vira.value == 9)
			this.manilha = new Card(0, Naipe.P);
		else
			this.manilha = new Card(vira.value + 1, Naipe.P);
	}

	public State(State state, int round) {
		this.p1 = state.p1;
		this.p2 = state.p2;
		this.playsp1 = state.playsp1;
		this.playsp2 = state.playsp2;
		this.deck = state.deck;
		this.vira = state.vira;
		this.manilha = state.manilha;
		this.round = round;
	}

	public Card getVira() {
		return vira;
	}

	public Card getManilha() {
		return manilha;
	}

	public Double getScore() {
		if (p1.score < p2.score)
			return 1.0;
		if (p1.score > p2.score)
			return -1.0;
		return 0.0;
	}

	public int checkWin() {
		if (p1.score == 2) {
			return -1; // Human wins
		}
		if (p2.score == 2) {
			return 1; // Frank wins
		}
		return 0;
	}

	public void atualizaScore() {
		p1.score = 0;
		p2.score = 0;
		for (int i = 0; i < (round+1); i++) {
			boolean manilha1 = playsp1.get(i).isManilha(manilha);
			boolean manilha2 = playsp2.get(i).isManilha(manilha);
			if (manilha1 && manilha2) {
				if (playsp1.get(i).naipe.compareTo(playsp2.get(i).naipe) == 1) {
					if (i == 0)
						fezPrimeira = true;
					p1.score++;
					startHuman = true;
				} else {
					if (i == 0)
						fezPrimeira = false;
					p2.score++;
					startHuman = false;
				}
			} else {
				if (playsp1.get(i).value > playsp2.get(i).value) {
					if (i == 0)
						fezPrimeira = true;
					p1.score++;
					startHuman = true;
				}
				if (playsp1.get(i).value < playsp2.get(i).value) {
					if (i == 0)
						fezPrimeira = false;
					p2.score++;
					startHuman = false;
				}
				if (playsp1.get(i).value == playsp2.get(i).value) {
					if (i >= 1) {
						if (fezPrimeira)
							p1.score = 2;
						else
							p2.score = 2;
					}else if(i == 0) {
						p1.score++;
						p2.score++;
					}
				}
			}
		}
	}
	
	public void printHandP1() {
		System.out.println("Sua mão: ");
		System.out.println();
		for (int i = 0; i < p1.hand.size(); i++) {
			System.out.print(" Valor: " + p1.hand.get(i).rep + " Naipe: " + p1.hand.get(i).naipe + " | ");
		}
	}
	
	public void printState() {
		System.out.println("Vira: " + vira.rep + " manilha: " + manilha.rep);

		System.out.println("Sua mão:");
		System.out.println();
		for (int i = 0; i < p1.hand.size(); i++) {
			System.out.print(" Valor: " + p1.hand.get(i).rep + " Naipe: " + p1.hand.get(i).naipe + " | ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Mão do Frank: ");
		System.out.println();
		for (int i = 0; i < p2.hand.size(); i++) {
			System.out.print(" Valor: " + p2.hand.get(i).rep + " Naipe: " + p2.hand.get(i).naipe + " | ");
		}
		System.out.println();
	}
}
