public class FreeCellPile extends Pile{
	public FreeCellPile(){
		super(1, true);
	}

	@Override
	public boolean add(Card c){
		if(pile.size() < cardLimit){
			pile.add(c);
			return true;
		}
		System.err.println("The FreeCell can only hold one card");
		return false;
	}

	@Override
	public Card poll(){
		Card c = pile.get(0);
		pile.clear();
		return c;
	}
}
