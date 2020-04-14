package truco;

import java.util.ArrayList;
import java.util.List;

public class State {
	Player p1,p2;
	Deck deck;
	Card vira;
	Card manilha;
	int round;
	List<Card> playsp1 = new ArrayList<Card>();
	List<Card> playsp2 = new ArrayList<Card>();
	
	public State(Player p1, Player p2, Deck deck, int round) {
		this.p1 = p1;
		this.p2 = p2;
		this.deck = deck;
		this.vira = deck.shuffleCard();
		this.round = round;
		if(vira.value == 9)
			this.manilha = new Card(0, Naipe.P);
		else
			this.manilha = new Card(vira.value+1, Naipe.P);
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
		Card cartaP1 = playsp1.get(round);
		Card cartaP2 = playsp2.get(round);
		if(cartaP1.isManilha(manilha) && cartaP2.isManilha(manilha)) {
			if(cartaP1.naipe.compareTo(cartaP2.naipe) == 1)
				return -1.0;
			else
				return 1.0;
		}
		if(cartaP1.value < cartaP2.value)
			return 1.0;
		if(cartaP1.value > cartaP2.value)
			return -1.0;
		
		return 0.0;
	}
	
	public void printState() {
		System.out.println("Vira: "+vira.rep + " manilha: "+ manilha.rep);
		
		System.out.println("Mao do jogador 1:");
		System.out.println();
		for(int i = 0; i < p1.hand.size(); i++) {
			System.out.print(" Valor: "+p1.hand.get(i).rep +" Naipe: "+p1.hand.get(i).naipe +",");
		}
		System.out.println();
		System.out.println();
		System.out.println("Mao do jogador 2: ");
		System.out.println();
		for(int i = 0; i < p2.hand.size(); i++) {
			System.out.print(" Valor: "+p2.hand.get(i).rep +" Naipe: "+p2.hand.get(i).naipe+",");
		}
		System.out.println();
	}
}
