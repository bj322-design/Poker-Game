import java.util.Scanner;

public class Game {
	public static void main(String[]agrs) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("===================Welcome to Poker=================="
				+ "\n=====The rules are the same that of Texas Holdem====="
				+ "\n====================================================="
				+ "\n\nHow many Players are in your current game?: ");
		
		int amtPlayers = scan.nextInt();
//int amtPlayers = 22; //this is for testing plz comment out when done
		while(amtPlayers < 2 || amtPlayers > 22) {
			System.out.print("\n" + amtPlayers + " is not a valid choice\nPlease input the amount of players (2 or greater and 22 or less): ");
			amtPlayers = scan.nextInt(); 
		}
		
		Player[] players = new Player[amtPlayers];
		Card[] deck = Card.newDeck();
		
		for(int i = 0; i < amtPlayers; i ++) {
			players[i] = new Player();
		}
		
		Card.deal(deck, players); //disable this and enable below for fake deck
		
//Fake_Deck.deal(deck, players); //enable this for fake deck
		
		Card[] communityCards = Card.communityCards(amtPlayers, deck);
		
		printPlayerCards(players); 
String output = printPlayersCardsFile(players); //for Testing to output to a txt file
		
		printCommunityCards(communityCards); 
output += printCommunityCardsFile(communityCards); //for Testing to output to a txt file
		
		Winner_Logic wl = new Winner_Logic(players, communityCards); 

//Every output here shouldn't be there this is just for testing for the most part
output += "\n"; 

System.out.println();
		

		boolean pocket;
		for(int i = 0; i < players.length; i++) {
			pocket = wl.pocketCheck(i);
			System.out.println("Player " + (i + 1) + " has pockets? " + pocket); 
output += "Player " + (i + 1) + " has pockets? " + pocket + "\n";
		} 

		
System.out.println(); 
output += "\n"; 

		boolean pair;
		for(int i = 0; i < players.length; i ++) {
			pair = wl.pairCheck(i); 
			System.out.println("Player " + (i + 1) + " has at least 1 pair? " + pair); 
output += "Player " + (i + 1) + " has at least 1 pair? " + pair + "\n"; 
		}
		
System.out.println();
output += "\n";
		
		boolean threeOfAKind;
		for(int i = 0; i < players.length; i ++) {
			threeOfAKind = wl.threeOfAKind(i); 
			System.out.println("Player " + (i + 1) + " has at least 1 three of a kind? " + threeOfAKind);
output += "Player " + (i + 1) + " has at least 1 three of a kind? " + threeOfAKind + "\n";
		}
		
System.out.println();
output += "\n";
		
		boolean fourOfAKind;
		for(int i = 0; i < players.length; i++) {
			fourOfAKind = wl.fourOfAKind(i); 
			System.out.println("Player " + (i + 1) + " has a four of a kind? " + fourOfAKind);
output += "Player " + (i + 1) + " has a four of a kind? " + fourOfAKind + "\n";
		}
		
System.out.println();
output += "\n"; 

		boolean fullHouse;
		for(int i = 0; i < players.length; i ++) {
			fullHouse = wl.fullHouse(i);
			System.out.println("Player " + (i + 1) + " has a full house? " + fullHouse);
output += "Player " + (i + 1) + " has a full house? " + fullHouse + "\n";
		}

		
		
System.out.println();
output += "\n";
		boolean straight;
		for(int i = 0; i < players.length; i++) {
			straight = wl.straight(i); 
			System.out.println("Player " + (i + 1) + " has a straight? " + straight);
output += "Player " + (i + 1) + " has a straight? " + straight + "\n"; 
		}
		
		
System.out.println();
output += "\n";
		boolean flush; 
		for(int i = 0; i < players.length; i++) {
			flush = wl.flush(i);
			System.out.println("Player " + (i + 1) + " has a flush? " + flush);
output += "Player " + (i + 1) + " has a flush? " + flush + "\n";
		}
		
		
System.out.println();
output += "\n";
		boolean straightFlush;
		for(int i = 0; i < players.length; i ++) {
			if(wl.flush(i) && wl.straight(i)) {
				straightFlush = true; 
			}else {
				straightFlush = false; 
			}
			
			System.out.println("Player " + (i + 1) + " has a straight flush? " + straightFlush);
output += "Player " + (i + 1) + " has a straight flush " + straightFlush + "\n"; 
		}
		
System.out.println();
output += "\n";
		boolean royalFlush;
		for(int i = 0; i < players.length; i++) {
			royalFlush = wl.royalFlush(i);
			System.out.println("Player " + (i + 1) + " has a royal flush? " + royalFlush);
output += "Player " + (i + 1) + " has a royal flush " + royalFlush + "\n"; 
		}
		
		
System.out.println();
output += "\n";
		int highCard = -1;
		for(int i = 0; i < players.length; i ++) {
			highCard = wl.highCard(i);
			System.out.println("Player " + (i + 1) + " has a high card of: " + highCard); 
output += "Player " + (i + 1) + " has a high card of: " + highCard; 
		}
		
		//comparing to find winner
System.out.println();
		Player.setHands(players, communityCards);
		int winner = wl.win();
		System.out.println("Player " + (winner + 1) + " wins with a " + players[winner].getHand());
		
output += "\n****************************************\n\n";
Print_To_File.filePrinter(output);
		
	}//end of main method
	
	public static void printPlayerCards(Player[] players) {
		for(Player p: players) {
			
			System.out.println(p.getName() + ":"); 
			System.out.println("\t" + p.getCard(1).toString()); 
			System.out.println("\t" + p.getCard(2).toString());
			
			System.out.println(); 
		}
	}
	
	public static String printPlayersCardsFile(Player[]players) {
		String output = "";
		for(Player p: players) {
			output += p.getName() + ":\n";
			output += "\t" + p.getCard(1).toString() + "\n";
			output += "\t" + p.getCard(2).toString() + "\n\n";
		}
		return output;
	}
	
	public static void printCommunityCards(Card[] communityCards) {
		System.out.println("Community Cards:");
		for(Card c: communityCards) {
			System.out.println("\t" + c.toString()); 
		}
	}
	
	
	public static String printCommunityCardsFile(Card[] communityCards) {
		String output = "Commmunty Cards:\n";
		for(Card c: communityCards) {
			output += "\t" + c.toString() + "\n";
		}
		return output;
	}
	
	
}
