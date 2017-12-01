public enum Suit implements Comparable<Suit>{
	CLUB(0), DIAMOND(1), HEART(2), SPADE(3), DUMMY(4);

	final int value;
	Suit(int value){
		this.value = value;
	}
}
