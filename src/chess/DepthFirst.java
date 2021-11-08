package chess;

import java.util.ArrayList;
import java.util.Random;

public class DepthFirst implements Player {
	private static final int MAX_DEPTH = 4;
	private static int _depth = 0;

	@Override
	public int choose(Game game, ArrayList<Move> legalMoves) {
		ArrayList<Integer> candidatMoves = new ArrayList<>();
		Random random;
		Game simulation;
		int maxMove;
		double maxValue = -2*King.VALUE;
		double value;
		double othersValue = 0;
		_depth += 1;
		for (int i = 0; i < legalMoves.size(); i += 1) {
			simulation = new Game(game, new DepthFirst(), new DepthFirst());
			value = simulation.playMove(i, legalMoves);
			if (_depth <= MAX_DEPTH && value < King.VALUE) {
				othersValue = simulation.playTurn();
			}
			if (Math.abs(value - othersValue - maxValue) < 0.00001) {
				candidatMoves.add(i);
			} else if (value - othersValue > maxValue) {
				maxValue = value - othersValue;
				candidatMoves.clear();
				candidatMoves.add(i);
			}
		}
		random = new Random();
		maxMove = candidatMoves.get(random.nextInt(candidatMoves.size()));
		_depth -= 1;
		return maxMove;
	}
	
}
