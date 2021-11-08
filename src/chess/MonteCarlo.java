package chess;

import java.util.ArrayList;

public class MonteCarlo implements Player {

	@Override
	public int choose(Game game, ArrayList<Move> legalMoves) {
		Game simulation;
		long end = System.currentTimeMillis() + 15*1000;
		int counter = 0;
		while (System.currentTimeMillis() < end) {
			simulation = new Game(game, new Dumb(), new Dumb());
			simulation.run();
			counter += 1;
		}
		System.out.println("MonteCarlo algorithm ran " + counter + " games.");
		game.getCurrentNode().cleanTree();
		System.gc();
		return game.getCurrentNode().getMostWinrateMove();
	}
}
