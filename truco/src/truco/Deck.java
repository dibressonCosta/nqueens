package truco;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Deck {
	List<Card> deck = new ArrayList<Card>();
	Random randomGenerator = new Random();
	int n = 40;
	public Deck() {
		for(int i = 0; i<4; i++) {
			for(int j = 0; j<10; j++) {
				Card card = new Card(j, Naipe.values()[i]);
				deck.add(card);
			}
		}
	}
	
	public Card getCard(int index) {
		return deck.get(index);
	}
	public Card shuffleCard() {
		int randomIndex = randomGenerator.nextInt(n);
		n--;
		return deck.remove(randomIndex);
	}
	public void printDeck() {
		for(int i = 0; i<n; i++) {
			Card card = deck.get(i);
			System.out.println("Valor: " + card.rep + " Naipe: " +card.naipe);
		}
	}
	public static void main(final String[] args) {
		Deck deck = new Deck();
		deck.printDeck();
		
	}
}
