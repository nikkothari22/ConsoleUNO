package UNO;

import java.util.Random;

/* Card Codes:
 * Colour: R-0, Y-1, G-2, B-3
 * Card Type: 0,1,2,3,4,5,6,7,8,9,10(Skip), 11(Reverse), 12(Draw +2)
 * 13 (Wild), 14 (Draw +4 Wild)
 */

public class Card {
	int colour;
	int cardType;
	
	
	Card(){ //Constructor
		colour=0;
		cardType = 0;
		
	}
	//Create a Random Card and return it.
	public Card setRandomCard(){
			Card c = new Card();
			Random rand = new Random();	
			colour = rand.nextInt(4);
			cardType = rand.nextInt(15);
			return c;
			
	}
	
	//Convert Card colour code to String
	public String cardColourCodeConvert(){
		String col="";
		if(this.cardType == 13 || this.cardType==14){
			return "";
		}
		else{
			switch(this.colour){
			case 0:col="Red"; 
			break;
			case 1: col="Yellow"; 
			break;
			case 2: col="Green"; 
			break;
			case 3: col="Blue"; 
			break;
			default: col="";
			}
			return col;
		}
	}
	
	//Convert Card Type code to String
	public String cardTypeCodeConvert(){
		String type="";
		
			switch(this.cardType){
			case 0:type="0"; 
			break;
			case 1: type="1"; 
			break;
			case 2: type="2"; 
			break;
			case 3: type="3"; 
			break;
			case 4: type="4"; 
			break;
			case 5: type="5"; 
			break;
			case 6: type="6"; 
			break;
			case 7: type="7"; 
			break;
			case 8: type="8"; 
			break;
			case 9: type="9"; 
			break;
			case 10: type="Skip"; 
			break;
			case 11: type="Reverse"; 
			break;
			case 12: type="Draw Two (+2)"; 
			break;
			case 13: type="Wild Card"; 
			break;
			case 14: type="Draw Four (+4) with Wild Card"; 
			break;
			
			default: type="";
			}
			return type;
	
	
	}
	
}
