package chess;

import java.util.ArrayList;
import java.util.Random;

public class DepthWithMonteCarlo implements Player {
	private static final int MAX_DEPTH = 4;
	private static int _depth = 0;
	private static int _moveCounter = 0;

	@Override
	public int choose(Game game, ArrayList<Move> legalMoves) {
		ArrayList<Integer> candidatMoves = new ArrayList<>();
		Random random;
		Game simulation;
		int maxMove;
		double maxValue = -2*King.VALUE;
		double value;
		double othersValue = 0;
		long end;
		int counter;
		_depth += 1;
		for (int i = 0; i < legalMoves.size(); i += 1) {
			_moveCounter += 1;
			simulation = new Game(game, new DepthWithMonteCarlo(), new DepthWithMonteCarlo());
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
		if (_depth == 1) {
			System.out.println("______________________________________________________________________________");
			System.out.println("Depth First Search evluated " + _moveCounter + " possible moves (" + legalMoves.size() + " on the immediate turn).");
			System.out.println("Depth First Search gave " + candidatMoves.size() + " candidat moves with value " + maxValue + ".");
			counter = 0;
			end = System.currentTimeMillis() + 15*1000;
			while (System.currentTimeMillis() < end) {
				random = new Random();
				simulation = new Game(game, new Dumb(), new Dumb());
				simulation.playMove(candidatMoves.get(random.nextInt(candidatMoves.size())), legalMoves);
				simulation.run();
				counter += 1;
			}
			System.out.println("Monte Carlo simulated " + counter + " games on candidat moves.");
			maxMove = game.getCurrentNode().getMostWinrateMoveFrom(candidatMoves);
			System.out.println("Monte Carlo chose the move with " + game.getCurrentNode().getChild(maxMove).getWinrate()*100 + "% win rate.");
			game.getCurrentNode().cleanTree();
			System.gc();
			_moveCounter = 0;
		} else {
			random = new Random();
			maxMove = candidatMoves.get(random.nextInt(candidatMoves.size()));
		}
		_depth -= 1;
		return maxMove;
	}
}
