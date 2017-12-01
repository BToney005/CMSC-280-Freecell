public class Card{
	int rank;
	Suit suit;
	boolean isRed;

	public Card(int rank, int suit){
		this.rank = rank;
		switch(suit){
			case 3: this.suit=Suit.SPADE; break;
			case 2: this.suit=Suit.HEART; break;
			case 1: this.suit=Suit.DIAMOND; break;
			case 0: this.suit=Suit.CLUB; break;
			default: this.suit=Suit.DUMMY; break;
		}
		switch(this.suit){
			case SPADE: isRed=false; break;
			case HEART: isRed=true; break;
			case DIAMOND: isRed=true; break;
			case CLUB: isRed=false; break;
			default: isRed=false; break;
		}
	}

	public Card(int rank, Suit suit){
		this(rank,suit.value);
	}

	public Card(){
		this(0,Suit.DUMMY);
	}

	public Card( String s ){
		char charRank = s.charAt(0);
		char charSuit = s.charAt(1);
		if(charRank == 'T')
			this.rank = 10;
		else if(charRank == 'J')
			this.rank = 11;
		else if(charRank == 'Q')
			this.rank = 12;
		else if(charRank == 'K')
			this.rank = 13;
		else if(charRank == 'A')
			this.rank = 1;
		else
			this.rank = Character.getNumericValue(charRank);

		if(charSuit == 'C')
			this.suit = Suit.CLUB;
		else if(charSuit == 'D')
			this.suit = Suit.DIAMOND;
		else if(charSuit == 'H')
			this.suit = Suit.HEART;
		else if(charSuit == 'S')
			this.suit = Suit.SPADE;
	}

	public String toString(){
		String retval="";
		switch(rank){
			case 1: retval+="A"; break;
			case 10: retval+="T"; break;
			case 11: retval+="J"; break;
			case 12: retval+="Q"; break;
			case 13: retval+="K"; break;
			default: retval+=rank; break;
		}
		switch(suit){
			case SPADE: retval+="S"; break;
			case HEART: retval+="H"; break;
			case DIAMOND: retval+="D"; break;
			case CLUB: retval+="C"; break;
			default: retval+=" "; break;
		 }
		return retval;
	}	
	
	@Override
	public boolean equals( Object other ){
		if( other instanceof Card )
			return this.rank == ((Card)other).rank && this.suit.value == ((Card)other).suit.value;
		return false;
	}

	public boolean equals( Card other ){
			return this.rank == other.rank && this.suit.value == other.suit.value;
	}
	
	public int compareTo( Card other ){
		if( this.suit.value < other.suit.value ){
			return -1;
		}else if( this.suit.value == other.suit.value ){
			if( this.rank < other.rank )
				return -1;
			else if( this.rank == other.rank )
				return 0;
			else
				return 1;
		}else{
			return 1;
		}
	}	
}
