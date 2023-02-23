import java.util.Arrays;

public class Winner_Logic {

	Player[] players; 
	Card[] communityCards;
	
	public Winner_Logic(Player[] players, Card[] communityCards) {
		this.players = players; 
		this.communityCards = communityCards; 
	}
	
	/**
	 * This checks if the player has two of the same card in their hand
	 * @param playerNum
	 * @return pocketCheck
	 */
	public boolean pocketCheck(int playerNum) {
		Card card1 = players[playerNum].getCard(1); 
		Card card2 = players[playerNum].getCard(2); 
		
		if(card1.getRank().equals(card2.getRank())) {
			return true; 
		}else {
			return false; 
		}
	}
	
	/**
	 * Checks to see if the user has a pair (be it a pocket or with community cards)
	 * @param playerNum
	 * @return pairCheck
	 */
	public boolean pairCheck(int playerNum) {
		boolean pair = false; 
		
		if(pocketCheck(playerNum)) {
			return true; 
		}
		
		Card card1 = players[playerNum].getCard(1); 
		Card card2 = players[playerNum].getCard(2);
		
		for(int i = 0; i < communityCards.length; i ++) {
			if(card1.getRank().equals(communityCards[i].getRank())){
				return true; 
			}
			if(card2.getRank().equals(communityCards[i].getRank())) {
				return true;
			}
		}
		
		return false; 
	}
	
	/**
	 * Overloading the pair method to allow a specific card to be chosen to compare
	 * @param playerNum
	 * @param card
	 * @return
	 */
	public boolean pairCheck(Card card) {
		boolean pair = false;
		
		for(int i = 0; i < communityCards.length; i++) {
			if(card.getRank().equals(communityCards[i].getRank())){
				return true; 
			}
		}
		return false; 
	}
	
	/**
	 * Checks to see if a player has a 3 of a kind
	 * @param playerNum
	 * @return threeOfAKind
	 */
	public boolean threeOfAKind(int playerNum) {
		boolean threeOfAKind = false; 
		int test1 = 1; 
		int test2 = 1; 
		
		Card card1 = players[playerNum].getCard(1); 
		Card card2 = players[playerNum].getCard(2); 
		
		if(pocketCheck(playerNum)) {
			test1 ++;
			test2 ++;
		}
		
		//for Card num 1
		for(int i = 0; i < communityCards.length; i++) {
			if(card1.getRank().equals(communityCards[i].getRank())) {
				test1 ++; 
			}
		}
		
		if(test1 >= 3) {
			threeOfAKind = true; 
		}
		
		//for Card num 2
		for(int i = 0; i < communityCards.length; i++) {
			if(card1.getRank().equals(communityCards[i].getRank())) {
				test2 ++; 
			}
		}
		
		if(test2 >= 3) {
			threeOfAKind = true; 
		}
		
		return threeOfAKind; 
	}
	
	/**
	 * Overloading the threeOfAKind method to allow a specific card to be compared
	 * @param playerNum
	 * @param card
	 * @return
	 */
	public boolean threeOfAKind(Card card) {
		int test = 0; 
		
		for(int i = 0; i < communityCards.length; i++) {
			if(card.getRank().equals(communityCards[i].getRank())) {
				test ++; 
			}
		}
		
		if(test >= 3) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Checks to see if a player has four of a kind
	 * @param playerNum
	 * @return fourOfAKind
	 */
	public boolean fourOfAKind(int playerNum) {
		boolean fourOfAKind = false;
		
		int test1 = 1; 
		int test2 = 1; 
		
		Card card1 = players[playerNum].getCard(1); 
		Card card2 = players[playerNum].getCard(2); 
		
		if(pocketCheck(playerNum)) {
			test1 ++;
			for(int i = 0; i < communityCards.length; i ++) {
				if(card1.equals(communityCards[i])) {
					test1 ++; 
				}
			}
			
			
		}else {
			//for Card num 1
			for(int i = 0; i < communityCards.length; i++) {
				if(card1.getRank().equals(communityCards[i].getRank())) {
					test1 ++; 
				}
			}
			
			if(test1 == 4) {
				fourOfAKind = true; 
			}
			
			//for Card num 2
			for(int i = 0; i < communityCards.length; i++) {
				if(card1.getRank().equals(communityCards[i].getRank())) {
					test2 ++; 
				}
			}
			
			if(test2 == 4) {
				fourOfAKind = true; 
			}
			
		}
		
		
		return fourOfAKind; 
	}
	
	/**
	 * Checks to see if the user has a full house
	 * @param playerNum
	 * @return
	 */
	public boolean fullHouse(int playerNum) {
		Card card1 = players[playerNum].getCard(1); 
		Card card2 = players[playerNum].getCard(2);
		
		boolean c1ThreeOfAKind = threeOfAKind(card1);
		boolean c2ThreeOfAKind = threeOfAKind(card2);
		
		//with card1
		if(c1ThreeOfAKind) {
			boolean c2Pair = pairCheck(card2);
			
			if(c2Pair) {
				return true; 
			}
		}
		
		//with card2
		if(c2ThreeOfAKind) {
			boolean c1Pair = pairCheck(card1);
			
			if(c1Pair) {
				return true; 
			}
		}
		
		
		return false;
	}
	

	public boolean flush(int playerNum) {
		int test = 0; 
		
		Card card1 = players[playerNum].getCard(1); 
		Card card2 = players[playerNum].getCard(2); 
		
		if(card1.getSuit().equals(card2.getSuit())){
			test += 2;
		}
		
		for(int i = 0; i < communityCards.length; i++) {
			if(card1.getSuit().equals(communityCards[i].getSuit()) || card2.getSuit().equals(communityCards[i].getSuit())) {
				test ++; 
			}
		}
		
		if(test >= 5) {
			return true;
		}
		
		
		return false; 
	}
	
	
	public boolean straight(int playerNum) {
		int[] playerCardRanks = new int[2];
		int[] communityCardsRanks = new int[5];
		
		Card card1 = players[playerNum].getCard(1); 
		Card card2 = players[playerNum].getCard(2);
		playerCardRanks[0] = Card.getNumRank(card1);
		playerCardRanks[1] = Card.getNumRank(card2);
		
		for(int i = 0; i < communityCards.length; i ++) {
			communityCardsRanks[i] = Card.getNumRank(communityCards[i]);
		}
		
		int[] straightDetector = new int[playerCardRanks.length + communityCardsRanks.length];
		
		System.arraycopy(playerCardRanks, 0, straightDetector, 0, playerCardRanks.length);
		System.arraycopy(communityCardsRanks, 0, straightDetector, playerCardRanks.length, communityCards.length);
		
		Arrays.sort(straightDetector);
		
		int count = 1; 
		for(int i = 1; i < straightDetector.length; i++) {
			if(straightDetector[i] == straightDetector[i - 1] + 1) {
				count ++;
				
			}else if(straightDetector[i] != straightDetector[i - 1]) {
				count = 1; 
			}
			
			if(count == 5) {
				return true;
			}
		}
		
		return false; 
	}
	
	
	public boolean straightFlush(int playerNum) {
		if(straight(playerNum) && flush(playerNum)) {
			return true;
		}
		return false;
	}
	
	
	
	public boolean royalFlush(int playerNum) {
		if(straight(playerNum) && flush(playerNum)) {
		
			int[] playerCardRanks = new int[2];
			int[] communityCardsRanks = new int[5];
			
			Card card1 = players[playerNum].getCard(1);
			Card card2 = players[playerNum].getCard(2);
			playerCardRanks[0] = Card.getNumRank(card1);
			playerCardRanks[1] = Card.getNumRank(card2);
			
			for(int i = 0; i < communityCards.length; i ++) {
				communityCardsRanks[i] = Card.getNumRank(communityCards[i]);
			}
			
			int[] royalFlush = new int[playerCardRanks.length + communityCardsRanks.length];
			
			System.arraycopy(playerCardRanks, 0, royalFlush, 0, playerCardRanks.length);
			System.arraycopy(communityCardsRanks, 0, royalFlush, playerCardRanks.length, communityCards.length);
			
			Arrays.sort(royalFlush);
			
			int[] comparison = {1, 10, 11, 12, 13};
			
			boolean flag = false;
			
			for(int i = 0; i < royalFlush.length; i ++) {
				if(comparison[i] == royalFlush[i]) {
					flag = true;
				}else {
					return false;
				}
			}
			
			if(flag == true) {
				return true; 
			}
		
		
		}
		return false;
	}
	
	
	public int highCard(int playerNum) {
		Card card1 = players[playerNum].getCard(1);
		int card1Int = Card.getNumRank(card1);
		
		Card card2 = players[playerNum].getCard(2);
		int card2Int = Card.getNumRank(card2);
		
		int highCard = -1;
		if(card1Int == 1 || card2Int == 1) {
			return 1;
		}
			if(card1Int >= card2Int) {
				highCard = card1Int;
			}else {
				highCard = card2Int;
			}
			
			//comparing to CC
			int[] communityCardsInt = new int[5]; 
			for(int i = 0; i < communityCardsInt.length; i++) {
				communityCardsInt[i] = Card.getNumRank(communityCards[i]);
			}
			
			for(int i = 0; i < communityCardsInt.length; i++) {
				if(highCard < communityCardsInt[i]) {
					highCard = communityCardsInt[i];
				}
			}
			return highCard; 
		
	}
	
	public int highCard(Player player) {
		Card card1 = player.getCard(1);
		int card1Int = Card.getNumRank(card1);
		
		Card card2 = player.getCard(2);
		int card2Int = Card.getNumRank(card2);
		
		int highCard = -1;
		if(card1Int == 1 || card2Int == 1) {
			return 1;
		}
			if(card1Int >= card2Int) {
				highCard = card1Int;
			}else {
				highCard = card2Int;
			}
			
			//comparing to CC
			int[] communityCardsInt = new int[5]; 
			for(int i = 0; i < communityCardsInt.length; i++) {
				communityCardsInt[i] = Card.getNumRank(communityCards[i]);
			}
			
			for(int i = 0; i < communityCardsInt.length; i++) {
				if(highCard < communityCardsInt[i]) {
					highCard = communityCardsInt[i];
				}
			}
			return highCard; 
		
	}
	
	public boolean twoPair(int playerNum) {
		Card card1 = players[playerNum].getCard(1);
		Card card2 = players[playerNum].getCard(2); 
		
		if((pocketCheck(playerNum) && ccPair()) || (pairCheck(card1) && pairCheck(card2))) {
			return true; 
		}
		return false;
	}
	
	public boolean ccPair() {
		Card ccCard1 = communityCards[0];
		Card ccCard2 = communityCards[1];
		Card ccCard3 = communityCards[2];
		Card ccCard4 = communityCards[3];
		Card ccCard5 = communityCards[4];
		
		if(ccCard1.getRank().equals(ccCard2.getRank()) || ccCard1.getRank().equals(ccCard3.getRank()) || ccCard1.getRank().equals(ccCard4.getRank()) || ccCard1.getRank().equals(ccCard5.getRank())) {
			return true;
		
		}else if(ccCard2.getRank().equals(ccCard3.getRank()) || ccCard2.getRank().equals(ccCard4.getRank()) || ccCard2.getRank().equals(ccCard5.getRank())) {
			return true;
		
		}else if(ccCard3.getRank().equals(ccCard4.getRank()) || ccCard3.getRank().equals(ccCard5)) {
			return true;
			
		}else if(ccCard4.getRank().equals(ccCard5.getRank())) {
			return true;
		
		}
		
			return false;
	}
	
	public int win() {
		int playerNumWinner = 0; 
		int winningHand = 12; //12 isn't a choice so it will always be lower 
		
		for(int i = 0; i < players.length; i ++) {
			if(players[i].getHand() < winningHand) {
				playerNumWinner = i;
				winningHand = players[i].getHand();
				
			}else if(players[i].getHand() == winningHand) {
				playerNumWinner = higherPair(players, playerNumWinner, i);
			}
		}
		
		
		return playerNumWinner;
	}
	
	/**
	 * Picks which player won for a pair
	 * @param players
	 * @param possWin1
	 * @param possWin2
	 * @return index_Of_Player
	 */
	public int higherPair(Player[] players, int possWin1, int possWin2) {
		Card cardValP1; 
		Card cardValP2;		
		
		if(!pocketCheck(possWin1) && !pocketCheck(possWin2)) {
			if(pairCheck(players[possWin1].getCard(1))) {
				cardValP1 = players[possWin1].getCard(1);
			
			}else {
				cardValP1 = players[possWin1].getCard(2); 
			}
			
			
			if(pairCheck(players[possWin2].getCard(1))) {
				cardValP2 = players[possWin2].getCard(1);
				
			}else {
				cardValP2 = players[possWin2].getCard(2);	
			}
			
			
		}else {
			cardValP1 = players[possWin1].getCard(1);
			cardValP2 = players[possWin2].getCard(1);
		}
		
		int val1 = Card.getNumRank(cardValP1);
		int val2 = Card.getNumRank(cardValP1);
		
		if((val1 == 1 || val1 > val2) && (val2 != 1)) {
			return possWin1;
		
		}else {
			return possWin2;
		}
	}
		
}
