import java.util.ArrayList;
public class Pile{
	final int cardLimit;
	ArrayList<Card> pile;
	boolean canTake;

	public Pile( final int cardLimit, boolean canTake){
	 this.cardLimit = cardLimit;
	 pile = new ArrayList<>(52); 
	 this.canTake = canTake;
	}

	public void initInsert( Card c ){
		pile.add(c);
	}

	public String toString(){
		StringBuilder str = new StringBuilder();
		for(Card c : pile)
			str.append(c + "\n");
		return str.toString();
	}

	public int size(){
		return pile.size();
	}

	public Card get( int i ){
		return pile.get(i);
	}

	public boolean add( Card c ){
		if( pile.size() < cardLimit ){
			pile.add(c);
			return true;
		}
		System.err.println("The pile has reached its card limit. Cannot add this card.");
		return false;
	}

	public boolean addAll( ArrayList<Card> c){
		if( pile.size() + c.size() < cardLimit ){
			pile.addAll(c);
			return true;
		}
		//error
		return false;
	}

	public void pull(){
		if(this.canTake)
			pile.remove(pile.size() - 1);
	}

	public void pull( int i ){
		if(this.canTake)
			pile.remove( i );
	}

	public void pullFrom( int i ){
		if(this.canTake)
			pile.subList(i,pile.size()).clear();
	}

	public Card peek(){
		if(pile.size() > 0)
			return pile.get(pile.size()-1);
		return new Card(0,Suit.DUMMY);
	}

	public Card poll(){
		if(this.canTake){
			Card c = new Card();
			c = pile.get( pile.size() - 1 );
			pile.remove( pile.size() - 1 );
			return c;
		}
		return new Card(0,Suit.DUMMY);
	}

	public ArrayList<Card> peekFrom( int i ){
			return new ArrayList<Card>(pile.subList(i,pile.size()));
	}
	

	boolean isFull(){
		return this.pile.size() == cardLimit;
	}

	boolean isEmpty(){
		return this.pile.isEmpty();
	}
}	
