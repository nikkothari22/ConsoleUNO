package UNO;
import java.util.*;
public class Player {
	String playerName;
	int playerNumber, numberOfCards;
	Player nextPlayer;
	Player previousPlayer;
	//Setting max number of cards allowed = 20
	Card playerDeck[] = new Card[20];
	private Scanner x = new Scanner(System.in);
	//Constructor
	Player(int playerNum){
		playerName = "";
		playerNumber = playerNum;
		numberOfCards = 0;
		getPlayerName();
	}
	//Get Player name from User
	public void getPlayerName(){
		System.out.print("Enter name of player "+(playerNumber+1)+": ");
		playerName = x.next();
		System.out.println();
	}
	
	//Add a card to player Deck
	public void addCardToDeck(Card c){
		numberOfCards++;
		playerDeck[numberOfCards-1] = c;
	}
	public Card removeCardFromDeck(int index){
		Card toReturn = playerDeck[index];
		for(;index<numberOfCards;index++){
			if(index==numberOfCards-1){
				playerDeck[index] = null;
			}
			else{
				playerDeck[index] = playerDeck[index+1];
			}		
		}
		numberOfCards--;
		return toReturn;
	}
	//Display Player Deck to player - call at every turn
	public void displayPlayerDeck(){
		
		System.out.println(playerName+"'s cards:");
		for(int i=0;i<numberOfCards;i++){
			System.out.println((i+1)+". "+playerDeck[i].cardColourCodeConvert()+" "+playerDeck[i].cardTypeCodeConvert());

			
		}
	}
	
}
