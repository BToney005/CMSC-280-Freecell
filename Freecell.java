import java.util.Random;
import java.util.Scanner;

public class Freecell{
	public static void main(String[] args){
		GameBoard game;
		if(args.length == 0)
			game = new GameBoard(Math.abs(new Random().nextInt()),false);
		else if(args[0].equals("cheat"))
			game = new GameBoard(true);
		else if(args[0].equals("test2"))
			game = new GameBoard("");
		else
			game = new GameBoard(Integer.parseInt(args[0]),false);
		System.out.println(game);
		game.play();
	}
}
