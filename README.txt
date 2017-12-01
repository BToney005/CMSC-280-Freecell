Project 2: Freecell
Brandon Toney
3 October 2013

Description: When run takes in an integer from the command line and uses it
	as a random seed. The program then outputs a game board which contains cards 
	that have been dealt out for the game, Freecell and allows a user to play the 
	full version of the game.

	The first row of output conains the following (F0 F1 F2 F3 C D H S)
	F[0-3] represent the Freecells. C,D,H,S represent homecells (CLUBS, DIAMONDS, 
	HEARTS, SPADES respectively). Each pile, by default, will read "0" beneath it.
	If a freecell has a card with in it, the correspong output would be something like this "5S"
	where 5 repesents a card's rank and the second character represents a card's suit.	

	The grid below contains the content of each of the cascade piles and assigns
	number to each cascade pile [0-7].

To Play: 
	> A user will first select a card, for Example: 5S
		Note: If the user types "quit" the game will terminate
			: If the card is not reachable or not valid the user will
			be prompted to choose a different card.
	> Next, the user will select a location for the card to be placed.
	> The available locations are as follows:
			0-7: the corresponding cascade pile
			8: Club's home cell
			9: Diamond's home cell
			10: Heart's home cell
			11: Spade's home cell
			12: F0 (freecells)
			13: F1
			14: F2
			15: F3
	> If a move is valid it will be executed, otherwise the user must reselect a valid card.
	> The game ends if the user types "quit" at the card selection screen OR when 
		all of the home cells are full.
		Note: see standard Freecell rules

To Compile: javac *.java

To Run: java Freecell (seed is random by default )
         OR
        java Freecell <seed>
         OR
        java Freecell cheat
         OR
        java Freecell test2


To Test: Test 1
	> Do the following (I suggest using two terminals):
	> run: java Freecell cheat
	> type: 7D
	> type: 4   <- this will move a tableu to an approriate pile in the cascade list
	> type: 7H  
	> type: 7   <- this however will not work because 7D and 8H are the same color
	> type: AC  
	> type: 8   <- this moves AC to the Club's home cell
	> type: AC  <- removal of this card is not allowed because it is in a freecell
	> type: AH  <- cannot remove this card because it is not the last card in the pile nor
									 is it part of a tableu
	> type: 2C  
	> type: 8   <- this card is moved to the homecell pile
	> type: 3C  
	> type: 8   <- this is an invalid move
	> type: 2D
	> type: 12
	> type: 2D
	> type: 13  <- shows that freecell movement is allowed
	> type: 2S
	> type: 13  <- this move should not work since a freecell can only hold one card at once
	> type: quit

Test 2:
	> run: java Freecell test2
	> type: KS
	> type: 11 <- should place the last remaining card in a freecell and cause the game to end

Also Try running:
	java Freecell            <- Random game
	java Freecell <seed>     <- Example: java Freecell 126
