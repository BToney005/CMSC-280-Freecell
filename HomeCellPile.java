public class HomeCellPile extends Pile{
	Suit suit;

	public HomeCellPile( Suit suit ){
		super(13, false);
		this.suit = suit;
	}

	public HomeCellPile(){
		this( Suit.DUMMY );
	}

	@Override
	public boolean add( Card c ){
		if(c.suit.value != this.suit.value){
			//error
			System.err.println("Wrong Suit");
			return false;
		}else if( this.isEmpty() ){
			pile.add(c);
			return true;
		}else if( c.rank - 1 != pile.get( pile.size() - 1 ).rank ){
			//error
			System.err.println("Value is too high");
			return false;
		}else if( pile.size() == cardLimit ){
			System.err.println("Can't excceed card limit");
			return false;
		}
		pile.add(c);
		return true;
	}
}
