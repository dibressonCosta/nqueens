package truco;

enum Naipe{O,E,C,P}
public class Card {
	int value;
	Naipe naipe;
	String rep;
	
	public Card(int value, Naipe naipe) {
		this.value = value;
		this.naipe = naipe;
		getRep();
	}
	public void getRep() {
		switch(value) {
		case 0: rep = "4";break;
		case 1: rep = "5";break;
		case 2: rep = "6";break;
		case 3: rep = "7";break;
		case 4: rep = "Q";break;
		case 5: rep = "J";break;
		case 6: rep = "K";break;
		case 7: rep = "A";break;
		case 8: rep = "2";break;
		case 9: rep = "3";break;
		}
	}
	public boolean isManilha(Card manilha) {
		if(this.value == manilha.value) {
			this.value = 10;
			return true;
		}else
			return false;
	}
	public static void main(final String[] args) {
		
		System.out.println("Teste");
		//System.out.println(carta.naipe);
	}
}
