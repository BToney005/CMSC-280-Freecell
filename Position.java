public class Position{
	Pile pile;
	int row;
	
	Position( Pile pile, int row){
		this.pile= pile;
		this.row = row;
	}

	Position(){
		this(null,0);
	}
}
