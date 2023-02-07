
public class Fake_Deck extends Card{

	public Fake_Deck(Card.Suit s, Card.Rank r) {
		super(s, r);
	}
	
	
	/**
	 * Fake Deck that does ace for both players then twos and threes for Community Cards
	 * @param deck
	 * @param players
	 */
	public static void deal(Card[] deck, Player[] players) {
		
		int deck_counter = 0; 
		for(int i = 0; i < (players.length); i++) {
			players[i].setCard(deck[deck_counter], 1);
			deck_counter++;
			
			players[i].setCard(deck[deck_counter], 2);
			deck_counter++;
			
		}
	}
	
	
	/*public static void deal(Card[] deck, Player[] players) {
		for(int i = 0; i < players.length; i++) {
			players[i].setCard(deck[1], 1);
			
			players[i].setCard(deck[1], 1);
		}
	}*/
	

}
