package UNO;
import java.util.*;
public class Game {
	//Total Deck - Rows - colour, Columns - cardType
	//Extra Deck has to be set like a queue
		static int totalDeck[][]=new int[4][15];
		static int totalNumberOfCards=108;
		static int numberOfCardsPerPlayer;
		static Card extraDeck[];
		static Card playingCard;
		static int topExtraDeck=-1;
		static int bottomExtraDeck=-1;
		static int numberOfPlayers;
		static Player p0,a,b;
		static int gameDirection=0; //0 means up, 1 means down
		static Scanner x = new Scanner(System.in);


		//Check if Random Card is valid.
		protected static boolean checkRandomCardValidity(Card c){
			// If card is 0, Wild, or Draw Four Wild, only one card allowed.
			int type= c.cardType;
			int col = c.colour;
			boolean r = false;
			switch(type){
			case 0:
			case 13:
			case 14: if(totalDeck[col][type]<1){
				totalDeck[col][type]++;
				r= true;
			}
			break;
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:if(totalDeck[col][type]<2){
				totalDeck[col][type]++;
				r= true;
			}
			break;
			
			}
			return r;
		}
		
	
		public static void setCardsForPlayer(Player p){
			for(int i=0;i<numberOfCardsPerPlayer;i++){
				Card c= new Card();
				c.setRandomCard();
				if(checkRandomCardValidity(c)){
					p.addCardToDeck(c);
					
				}
				else{
					i--;
				}
			}
		}
		public static void setDoublyLinkedList(){
			//Setting Player Doubly Linked List.
			//p0 is head of linked list
			p0 = new Player(0);
			setCardsForPlayer(p0);
			a = new Player(1);
			setCardsForPlayer(a);
			p0.nextPlayer = a;
			a.previousPlayer=p0;
			
			for(int i=2;i<numberOfPlayers;i++){
				b= new Player(i);
				setCardsForPlayer(b);
				a.nextPlayer = b;
				b.previousPlayer = a;
				a=b;
				
			}
			p0.previousPlayer = a;
			a.nextPlayer = p0;
		}
		public static void gameStart(){
			//Getting relevant details from user
			do{
				System.out.println("Enter number of players:");
				numberOfPlayers = x.nextInt();
				if(numberOfPlayers == 1){
					System.out.println("Seriously? You wanna play alone? Try again!");
				}
				else if(numberOfPlayers < 1){
					System.out.println("You gotta be kidding!");
				}
			}while(numberOfPlayers<2);
			//At least 18 cards in the extra deck 
			do{
				System.out.println("Enter number of initial cards per player:");
				numberOfCardsPerPlayer = x.nextInt();
			}while(numberOfCardsPerPlayer*numberOfPlayers > 90);
			
			setDoublyLinkedList();
		}
		public static void displayAllPlayers(){
			a=p0;
			for(int i=0;i<numberOfPlayers;i++){
				a.displayPlayerDeck();
				a=a.nextPlayer;
			}
		}
		//setting ExtraDeck
		public static void setExtraDeck(){
			extraDeck = new Card[(totalNumberOfCards)];
			topExtraDeck++;
			for(int i=0;i<totalNumberOfCards-(numberOfPlayers*numberOfCardsPerPlayer);i++){
				Card c= new Card();
				c.setRandomCard();
				if(checkRandomCardValidity(c)){
					bottomExtraDeck++;
					extraDeck[i] = c;
					
				}
				else{
					i--;
				}
			}
		}
		//Set the first card for play from the Extra Deck
		public static void setFirstCard(){
			boolean isItOkay = false ;
			do{
				playingCard = popExtraDeck();
				if(playingCard.cardType == 13 || playingCard.cardType == 14){
					pushExtraDeck(playingCard);
					isItOkay = false;
				}
				else{
					isItOkay = true;
				}
				
			}while(!isItOkay);
			
			
		}
		public static void displayPlayingCard(){
			System.out.println("The Playing Card is:  "+playingCard.cardColourCodeConvert()+" "+playingCard.cardTypeCodeConvert());
		}
		//pop the extra deck
		public static Card popExtraDeck(){
			if(topExtraDeck==extraDeck.length-1){ //ExtraDeck empty
				System.out.println("No more cards left in the Extra Deck");
				return null;
			}
			else{
				topExtraDeck++;
				return extraDeck[topExtraDeck-1];
			}
		}
		public static void pushExtraDeck(Card c){
			if(bottomExtraDeck<=extraDeck.length-2){
				bottomExtraDeck++;
				extraDeck[bottomExtraDeck] = c;
			}
		}
		
		//playerTurn is a recursive function (Kinda risky tbh!)
		public static void playerTurn(Player p, int choice){
			displayPlayingCard();
			System.out.println(p.playerName+"'s turn! Choose your option");
			p.displayPlayerDeck();
			System.out.println("99. Draw card from extra deck.");
			{
			if(choice==99){
			System.out.println("100. Skip");
			}
			choice = x.nextInt();
			if(choice ==99){
				p.addCardToDeck(popExtraDeck());  		//player chooses to draw card from extra deck
				playerTurn(p,99);
			}
			else if(choice==100){
				if(gameDirection == 0){
					playerTurn(p.nextPlayer,0);
				}
				else if(gameDirection == 1 ){
					playerTurn(p.previousPlayer,0);
				}
			}
			else if(choice <= p.playerDeck.length){
				if(checkCardPlayable(p.playerDeck[choice-1])){
					pushExtraDeck(playingCard);
					playingCard = p.playerDeck[choice-1];
					p.removeCardFromDeck(choice-1);
					//Special Card processing
					if(playingCard.cardType == 10){ //Playing card is Skip
						System.out.println("Skipped!");
						if(gameDirection == 0){
							p=p.nextPlayer;
							System.out.println(p.playerName+" Skipped!");
							playerTurn(p.nextPlayer,0);
						}
						else if(gameDirection == 1 ){
							p=p.previousPlayer;
							playerTurn(p.previousPlayer,0);
						}
					}
					else if(playingCard.cardType == 11){ //Playing card is reverse
						System.out.println("Reversed!");
						if(gameDirection == 0){
							gameDirection = 1;
						}
						else if(gameDirection == 1 ){
							gameDirection = 0;
						}
						
						if(gameDirection == 0){
							playerTurn(p.nextPlayer,0);
						}
						else if(gameDirection == 1 ){
							playerTurn(p.previousPlayer,0);
						}
					}
					else if(playingCard.cardType == 12){ //Playing card is draw 2
						nextPlayerDrawsCards(p,2);
						if(gameDirection == 0){
							playerTurn(p.nextPlayer,0);
						}
						else if(gameDirection == 1 ){
							playerTurn(p.previousPlayer,0);
						}
					}
					else if(playingCard.cardType == 13){
						ifCardIsWild(p);
					}
					else if(playingCard.cardType == 14){
						ifCardIsWildDrawFour(p);
					}
					
					else{
					
					if(gameDirection == 0){
						playerTurn(p.nextPlayer,0);
					}
					else if(gameDirection == 1 ){
						playerTurn(p.previousPlayer,0);
					}
					
				}
				}
				else{
					System.out.println("Uh oh! You cannot play that card! Try again.");
					playerTurn(p,0);
				}
			}
			else{
				System.out.println("That's not even an option! Try again.");
				playerTurn(p,0);
			}
			}
				
		}
		
		private static void nextPlayerDrawsCards(Player p, int cards) {
			// TODO Auto-generated method stub
			if(gameDirection == 0){
				p = p.nextPlayer;
			}
			else if(gameDirection ==1){
				p = p.previousPlayer;
			}
			System.out.println(p.playerName+" draws "+cards+" cards. Cards are added to deck!");
			for(int i=0;i<cards;i++){
				p.addCardToDeck(popExtraDeck());
			}
		}


		public static void ifCardIsWildDrawFour(Player p) {
			// TODO Auto-generated method stub
			nextPlayerDrawsCards(p,4);
			ifCardIsWild(p);
			
		}


		public static void ifCardIsWild(Player p) {
			// TODO Auto-generated method stub
			int choice;
			System.out.println("Choose your colour:");
			System.out.println("1.Red\t2.Yellow\t3.Green\t4.Blue");
			choice = x.nextInt();
			playingCard.colour = choice-1;
			System.out.println("The colour has changed to "+playingCard.cardColourCodeConvert());
			playerTurn(p,0);
			
		}


		public static boolean checkCardPlayable(Card c) {
			// TODO Auto-generated method stub
			if(c.cardType == 13 || c.cardType == 14 || c.colour ==playingCard.colour || c.cardType == playingCard.cardType){
				return true;
			}
			else{
				return false;
			}
		}


		public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("***********************************************************");
		System.out.println("\t\t\tUNO!");
		System.out.println("***********************************************************");
		gameStart();
		displayAllPlayers();
		setExtraDeck();
		System.out.println("***********************************************************");
		System.out.println("\t\t\tLet's start!");
		System.out.println("***********************************************************");
		setFirstCard();
		playerTurn(p0,0);
		
	}

}
