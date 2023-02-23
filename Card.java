import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Card {
	public enum Suit {clubs, spades, hearts, diamonds};
	public enum Rank {ace, two, three, four, five, six, seven, eight, nine, ten, jack, queen, king};
	
	private Rank rank;
	private Suit suit;
	
	public enum HLS {higher, lower, same}; 
	
	public Card (Suit s, Rank r) {
		suit = s; rank = r;
	}
	
	/**
	 * This utilizes the values of the cards taken from getNumRank method and takes in two cards to compare. If p1 has a higher one, then 1 is returned. If p2 is higher, than 2 is. If same, 0 is returned
	 * @param p1
	 * @param p2
	 * @return winner(0_being_same)
	 */
	public static HLS higherRank(Card p1, Card p2) {
		if(getNumRank(p1) == getNumRank(p2)) {
			return HLS.same;
		}else if(getNumRank(p1) > getNumRank(p2)) {
			return HLS.higher;
		}else {
			return HLS.lower;
		}
	}
	
	
	/**
	 * Returns the suit of the Card
	 * @return suit
	 */
	public Suit getSuit() {
		return suit;
	}
	
	/**
	 * Returns the rank of the Card
	 * @return rank
	 */
	public Rank getRank() {
		return rank;
	}
	
	/**
	 * Takes in a card and returns as an int a value associated with cards (ace == 1, two == 2 ... king == 13)
	 * @param c
	 * @return val_Of_Card
	 */
	public static int getNumRank(Card c) {
		switch(c.getRank()){
			case ace:
				return 1;
			case two:
				return 2;
			case three:
				return 3;
			case four:
				return 4;
			case five:
				return 5;
			case six:
				return 6;
			case seven:
				return 7;
			case eight:
				return 8;
			case nine:
				return 9;
			case ten:
				return 10;
			case jack:
				return 11;
			case queen:
				return 12;
			case king:
				return 13;
			
			default: //this was just to make java happy otherwise it not (also just a double check as if -1 i screwed up)
				return -1;
		}
	}
	
	public static Rank getRankFromInt(int rank) {
		switch(rank) {
			case 1:
				return Rank.ace;
			
			case 2:
				return Rank.two;
			
			case 3:
				return Rank.three;
			
			case 4:
				return Rank.four;
				
			case 5:
				return Rank.five;
			
			case 6:
				return Rank.six;
				
			case 7:
				return Rank.seven;
			
			case 8:
				return Rank.eight;
			
			case 9:
				return Rank.nine;
				
			case 10:
				return Rank.ten;
				
			case 11:
				return Rank.jack;
				
			case 12:
				return Rank.queen;
			
			case 13:
				return Rank.king;
				
			default:
				return Rank.ace;
		}
	}
	
	/**
	 * Returns a card in a readable format
	 */
	public String toString() {
		return rank.toString() + " of " + suit.toString();
	}
	
	/**
	 * Creates a new deck of 52 playing cards using ranks and suits to create new card objects as it goes and returns it in a Cards array
	 * @return deck
	 */
	public static Card[] newDeck() { //retVal means return value
		Card[] retVal = new Card[52];
		int cardNumber = 0; 
		for(Rank r: Rank.values()) {
			for(Suit s: Suit.values()) {
				retVal[cardNumber++] = new Card(s, r);
			}
		}
		return retVal;
	}
	
	/**
	 * Takes a Card array (deck of cards) and shuffles them once by turning the array to an array list and back into the OG array
	 * @param c
	 */
	public static void shuffleDeck(Card[] c) {
		ArrayList <Card> toShuffle = new ArrayList<Card>();
		
		for(int i = 0; i < c.length; i++) {
			toShuffle.add(c[i]);
		}
		
		Collections.shuffle(toShuffle);
		
		for(int i = 0; i < c.length; i ++) {
			c[i] = toShuffle.get(i); 
		}
	}
	

	/**
	 * Deals the cards evenly to two different players (26 per player) 
	 * @param deck
	 * @param player1
	 * @param player2
	 */
	public static void deal(Card[] deck, Player[] players) {
		shuffleDeck(deck);
		int deck_counter = 0; 
		for(int i = 0; i < (players.length); i++) {
			players[i].setCard(deck[deck_counter], 1);
			deck_counter++;
			
			players[i].setCard(deck[deck_counter], 2);
			deck_counter++;
			
		}
	}
	
	public static Card[] communityCards(int amtPlayers, Card[] deck) {
		Card[] communityCards = new Card[5];
		int upToCardNum = (amtPlayers * 2) + 1; 
		
		communityCards[0] = deck[upToCardNum];
		upToCardNum ++;
		
		communityCards[1] = deck[upToCardNum];
		upToCardNum ++; 
		
		communityCards[2] = deck[upToCardNum];
		upToCardNum ++; 
		
		upToCardNum ++; //burn
		communityCards[3] = deck[upToCardNum];
		
		upToCardNum ++;
		upToCardNum ++; //burn
		communityCards[4] = deck[upToCardNum];
		
				
		return communityCards; 
	}
	
	
	
}
