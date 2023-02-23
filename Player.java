public class Player {
	
	private Card card_1;
	private Card card_2;
	static int player_num = 0; 
	private String name;  
	private int hand;
	
	public Player() {
		player_num ++;
		name = "Player " + player_num; 
	}
	
	public Player(Card card_1, Card card_2) {
		this.card_1 = card_1;
		this.card_2 = card_2;
	}
	
	/**
	 * returns the name of the Player (should be Player #)
	 * @return
	 */
	public String getName() {
		return name; 
	}
	
	/**
	 * Sets the card param to the card num in the nu param (1 or 2) 
	 * @param c
	 * @param cardNum
	 */
	public void setCard(Card c, int cardNum) {
		if(cardNum == 1) {
			card_1 = c;
		
		}else if(cardNum == 2) {
			card_2 = c; 
		}
	}
	
	public Card getCard(int cardNum) {
		try {
			if(cardNum == 1) {
				return card_1; 
				
			}else if(cardNum == 2){
				return card_2; 
			}
			
		}catch(Exception e){
			System.out.println("\nIm sorry that is not a valid card");
			return new Card(Card.Suit.spades , Card.Rank.ace);
		}
		return card_1;
		
	}
	
	public static void setHands(Player[] players, Card[] communityCards) {
		Winner_Logic wl = new Winner_Logic(players, communityCards); 
		
		for(int i = 0; i < players.length; i++) {
			if(wl.royalFlush(i)) {
				players[i].setHand(1);
			
			}else if(wl.straightFlush(i)) {
				players[i].setHand(2);
			
			}else if(wl.fourOfAKind(i)) {
				players[i].setHand(3);
			
			}else if(wl.fullHouse(i)) {
				players[i].setHand(4);
			
			}else if(wl.flush(i)) {
				players[i].setHand(5);
				
			}else if(wl.straight(i)) {
				players[i].setHand(6);
				
			}else if(wl.threeOfAKind(i)) {
				players[i].setHand(7);
			
			}else if(wl.twoPair(i)) {
				players[i].setHand(8);
				
			}else if(wl.pocketCheck(i)) {
				players[i].setHand(9);
				
			}else if(wl.pairCheck(i)) {
				players[i].setHand(10);
			
			}else {
				players[i].setHand(11);
			}
		}
	}
	
	public String handFinder(Player players, Player[] p, Card[] cc) {
		Winner_Logic wl = new Winner_Logic(p, cc);
		int hand = players.getHand();
		String output = "";
		
		if(hand == 1){
				output = "Royal Flush";
		
		}else if(hand == 2) {
				output = "Straight Flush";
		
		}else if(hand == 3) {
				output = "Four of kind of " + players.getCard(1);
		
		}else if(hand == 4) {
			output = "Full House";
		
		}else if(hand == 5) {
				output = "Flush " + (players.getCard(1)).getSuit();
			
		}else if(hand == 6) {
				output = "Stright"; 
		
		}else if(hand == 7) {
				if(wl.threeOfAKind(players.getCard(1))) {
					output = "Three of a kind of " + players.getCard(1).getRank();
				}else {
					output = "Three of a kind of " + players.getCard(2).getRank();
				}
		
		}else if(hand == 8) {
				output = "Two pairs";
		
		}else if(hand == 9) {
				output = "Pocket " + players.getCard(1).getRank();
		
		}else if(hand == 10) {
				if(wl.pairCheck(players.getCard(1))) {
					output = "Pair of " + players.getCard(1).getRank();
				}else {
					output = "Pair of " + players.getCard(2).getRank();
				}
				
		}else if(hand == 11) {
				output = Card.getRankFromInt(wl.highCard(players)) + " high";
		}
		
		return output;
	}
	
	public void setHand(int hand) {
		this.hand = hand; 
	
	}
	
	public int getHand() {
		return hand;
	}
	
	
}//end of Player class
