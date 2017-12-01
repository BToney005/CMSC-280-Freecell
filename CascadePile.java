public class CascadePile extends Pile{
	public CascadePile(){
		super(52, true);
	}

	public boolean canSelect( Card c  ){
		for( int i = 0; i < pile.size(); i++){
			if( c.equals(pile.get(i)) )
				return this.cardIsValidAt(i);
		}
		return false;
	}

	public boolean cardIsValidAt( int i ){
		/*if(i+1 == pile.size())
			return true;
		return false;*/
		if( i+1 == pile.size() )
			return true;
		else if( pile.get(i).rank-1 != pile.get(i+1).rank )
			return false;
		else if( pile.get(i).isRed == pile.get(i+1).isRed )
			return false;
		return cardIsValidAt(i+1);
	}

	@Override
	public boolean add( Card c ){
		if(pile.size() == 0)
			return true;
		else if( pile.get(pile.size()-1).isRed == c.isRed )
			return false;
		else if( pile.get(pile.size()-1).rank-1 != c.rank )
			return false;
		pile.add(c);
		return true;
	}	

}

