package truco;

import java.util.ArrayList;
import java.util.List;

public class Player {
	List<Card> hand = new ArrayList<Card>();
	Deck deck;
	int score;
	public Player(Deck deck) {
		this.deck = deck;
		this.score = 0;
		for(int i=0; i < 3 ; i++) {
			Card card = deck.shuffleCard();
			this.hand.add(card);
		}
	}
}
