# ConsoleUNO
Console based UNO game (Java)

## Basics:
An UNO game consists of cards – 4 colours (25 each)

### Cards:

1. **0 :** 1 card/colour.
2. **1 to 9 :** 2 cards/colour.
3. **Skip / Reverse / Draw 2 :** 2 cards/colour.
4. **Wild Cards :** 4 cards.
5. **"Draw Four" + Wild Cards :** 4 cards.

### Data Structures used : 

1)	Doubly Linked List for player objects
2)	Stack/queue for deck of cards

## Classes:

### 1. Card:

#### Variables: 

1.	int colour – contains color code of card (R-0, G-1, B-2, Y-3)
2.	int cardType – contains card type code
#### Methods/Functions:

1.	setRandomCard() – creates a random card (random colour and type). Returns the card object.
2.	cardColourCodeConvert() – returns the String equivalent of the card colour.
3.	cardTypeCodeConvert() – returns the String equivalent of the card type.

### 2. Player:

#### Variables:

1.	String playerName – contains player name
2.	Int playerNumber – contains playerNumber
3.	Int numberOfCards – contains total number of cards possessed by player 
4.	Card playerDeck[] - contains array of card objects which player has.

#### Methods/Functions: 
1.	getPlayerName() – accepts player name
2.	addCardToDeck(card) – adds parameter card to player deck
3.	removeCardFromDeck(int) – removes card from player deck
4.	displayPlayerDeck() – displays complete player deck.

### 3. Game:	

#### Variables: 

1.	int totalDeck[][] – contains information of all the cards used in the game.
2.	Card extraDeck[] – array of card objects for extra cards.
3.	Int topExtraDeck – indicates top of extraDeck stack
4.	Int bottomExtraDeck – indicates bottom of extraDeck stack
5.	Card playingCard – the card in play (a.k.a. reference card)
6.	Int numberOfPlayers – Total no. of players in the game
7.	Int totalNumberOfCards – total no. of cards (108)
8.	Int numberOfCardsPerPlayer – number of cards each player has at the start.
9.	Int gameDirection – used to reverse the flow of control of game
10.	Player p0: p0 is head of Linked List.

#### Functions/Methods:

1.	checkRandomCardValidity() – checks random card generated, then adds entry to totalDeck[][], then returns true or false.
2.	Main() – main function of program, start-point.
3.	setCardsForPlayer(Player) – sets initial random cards for player
4.	setDoublyLinkedList() – sets the linking of all the player objects
5.	gameStart() – multiple uses – accepts number of users, no. of cards
6.	displayAllPlayers() – displays details and card deck of every player object
7.	setExtraDeck() – sets the remaining extra deck as a doubly ended stack
8.	setFirstCard() – chooses the first card in gameplay
9.	displayPlayingCard() – displays the reference card object details
10.	popExtraDeck(), pushExtraDeck()
11.	playerTurn() – recursive function controlling game play
12.	nextPlayerDrawsCard() – for Draw 2 and Draw 4 cards
13.	ifCardIsWild() – accepts change of colour if Wild card is played.
14.	checkCardPlayable() – checks the card whether it can be played by comparing it to reference card.
