package truco;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Deck baralho = new Deck();
		Player p1 = new Player(baralho);
		Player p2 = new Player(baralho);
		State state = new State(p1,p2, baralho, 0);
		Search search = new Search(p1,p2,state);
		search.play();
		
	}

}
