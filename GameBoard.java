import java.util.ArrayList;
import java.util.Scanner;

public class GameBoard{
	Deck deck;
	CascadePile[] cascadePiles;
	FreeCellPile[] freeCells;
	HomeCellPile[] homeCells;
	Card selectedCard;
	Position position;
	boolean gameOver;

	/*NOTE: FOR TESTING ONLY*/
	public GameBoard(String s){
		this(0,true);
		homeCells[0] = new HomeCellPile(Suit.CLUB);
		homeCells[1] = new HomeCellPile(Suit.DIAMOND);
		homeCells[2] = new HomeCellPile(Suit.HEART);
		homeCells[3] = new HomeCellPile(Suit.SPADE);
		for(int i = 1; i < 14; i++){
			homeCells[0].initInsert(new Card(i,0));
			homeCells[1].initInsert(new Card(i,1));
			homeCells[2].initInsert(new Card(i,2));
			if( i !=13 )
				homeCells[3].initInsert(new Card(i,3));
		}

		for( int i = 0; i < cascadePiles.length; i++ ){
			cascadePiles[i] = new CascadePile();
		}
		cascadePiles[0].initInsert(new Card(13,3));
	}
	/*FOR TESTING ONLY ^*/

	public GameBoard( int seed, boolean flag){
		deck = new Deck(seed);
		cascadePiles = new CascadePile[8];
		freeCells = new FreeCellPile[4];
		homeCells = new HomeCellPile[4];
		position = new Position();
		gameOver = false;
		
		for( int i = 0; i < cascadePiles.length; i++ ){
			cascadePiles[i] = new CascadePile();
		}

		if(!flag){
			for( int i = 0; deck.hasNext(); i++){
				if(i==8) i=0;
				cascadePiles[i].initInsert(deck.next());
			}
		}else{
			this.cheat();
		}

		for( int i = 0; i < freeCells.length; i++ ){
			freeCells[i] = new FreeCellPile();
		}

		homeCells[0] = new HomeCellPile(Suit.CLUB);
		homeCells[1] = new HomeCellPile(Suit.DIAMOND);
		homeCells[2] = new HomeCellPile(Suit.HEART);
		homeCells[3] = new HomeCellPile(Suit.SPADE);
	
	}
	
	public GameBoard(boolean flag){
		this(0,flag);
	}

	public GameBoard(){
		this(0,false);
	}

	/*FOR TESTING ONLY*/
	public void cheat(){
		Card[] orderedDeck = { 
			new Card(1,0),new Card(1,1),new Card(1,3),new Card(1,2),new Card(13,2),new Card(13,3),new Card(13,1),new Card(13,0),
			new Card(7,1),new Card(7,0),new Card(7,2),new Card(7,3),new Card(12,3),new Card(12,2),new Card(12,0),new Card(12,1),
	    new Card(6,0),new Card(6,2),new Card(6,3),new Card(6,1),new Card(11,2),new Card(11,0),new Card(11,1),new Card(11,3),
			new Card(5,1),new Card(5,0),new Card(5,2),new Card(5,3),new Card(10,3),new Card(10,2),new Card(10,0),new Card(10,1),
			new Card(4,0),new Card(4,2),new Card(4,3),new Card(4,1),new Card(9,2),new Card(9,0),new Card(9,1),new Card(9,3),
			new Card(3,1),new Card(3,0),new Card(3,2),new Card(3,3),new Card(8,0),new Card(8,1),new Card(8,3),new Card(8,2),
			new Card(2,3),new Card(2,2),new Card(2,0),new Card(2,1)
		};
		for( int i = 0, j = 0; i<orderedDeck.length; i++, j++ ){
			if(j==8) j=0;
			cascadePiles[j].initInsert(orderedDeck[i]);
		}

	}
	/*FOR TESTING ONLY*/

	public void gameIsOver(){
		int i = 0;
		for( HomeCellPile hcp : homeCells ){
			if( hcp.isFull() )
				i++;
		}
		if(i==3)
			gameOver = true;
	}

	public String toString(){
		return String.format(" F0 F1 F2 F3  C  D  H  S \n %s %s %s %s  %d  %d  %d  %d\n\n0   1   2   3   4   5   6   7\n", freeCells[0].peek(), freeCells[1].peek(), freeCells[2].peek(), freeCells[3].peek(), homeCells[0].peek().rank, homeCells[1].peek().rank, homeCells[2].peek().rank, homeCells[3].peek().rank) + genBoard();
	}

	public String genBoard(){
		StringBuilder str = new StringBuilder(/*"0| "*/);
		boolean flag = false;
		for( int i = 0, j = 0; i >= 0; i++ ){
			flag = true;
			for( CascadePile p : cascadePiles ){
				if( p.size() > j ){
					flag = false;
					break;
				}
			}
			if(flag)
				break;
			if( i == 8 ){
				i = 0;
				j++;
				str.append(String.format(/*"\n%d| "*/"\n",j));
			}
			if( cascadePiles[i].size() <= j )
				str.append("    ");
			else
				str.append(cascadePiles[i].get(j)+"  ");	
		}
		str.delete(str.length()-5, str.length());
		return str.toString();
	}

	public void makeMove(){
		boolean flag = false; 
		int destination;
		Scanner s = new Scanner(System.in);
		do{
			do{
				System.out.println("Select a card, example 5D");
				String str = new String(s.next());
				selectedCard = new Card(str);
				if( str.equals("quit") ){
					this.gameOver = true;
					System.exit(0);
				}
			}while(!cardValid(selectedCard));

		//do{
			System.out.println("Where will you place " +  selectedCard +  " ?");
			System.out.println("Cascade Pile [0-7], Home Cell[8-11], Free Cell[12-15]"); 
			destination = s.nextInt();


			if( destination >= 0 && destination <= 7 ){
				if(cascadePiles[destination].addAll(position.pile.peekFrom(position.row))){
					flag = true;
					position.pile.pullFrom(position.row);
				}
			}else if( destination >= 8 && destination <= 11 ){
				if(homeCells[destination-8].add(position.pile.peek())){
					flag = true;
					position.pile.pull();
				}
			}else if( destination >= 12 && destination <= 15 ){
				if(freeCells[destination-12].add(position.pile.peek())){
					flag = true;
					position.pile.pull();
				}
			}
		}while(!flag);

    for( HomeCellPile hcp : homeCells ){
			if(!hcp.isFull())
				break;
			else{
				System.out.println("Congratulations, You Won!");
				System.exit(0);
			}

		}
		System.err.println(this);
	}

	public boolean cardValid( Card c ){
		for( int i = 0; i < cascadePiles.length; i++ ){
			for( int j = 0; j < cascadePiles[i].size(); j++ ){
				if( cascadePiles[i].get(j).equals(c) ){
					position = new Position(cascadePiles[i],j);
					return cascadePiles[i].cardIsValidAt(j);
				}
			}
		}

		for( int i = 0; i < freeCells.length; i++ ){
			if( freeCells[i].isFull() ){
				if( freeCells[i].get(0).equals(c) ){
					position = new Position(freeCells[i],0);
					return true;
				}
			}
		}

		return false;
	}

	public void play(){
		while(!gameOver){
			this.makeMove();
		}
	}
}
