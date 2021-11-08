package chess;

import java.util.ArrayList;
import java.util.Random;

public class Dumb implements Player{
	
	@Override
	public int choose(Game game, ArrayList<Move> legalMoves) {
		Random random = new Random();
		return random.nextInt(legalMoves.size());
	}
}
