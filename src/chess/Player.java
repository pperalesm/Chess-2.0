package chess;

import java.util.ArrayList;

public interface Player {
	public int choose(Game game, ArrayList<Move> legalMoves);
}
