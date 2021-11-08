package chess;

import java.util.ArrayList;

public class Bishop extends Piece {
	private static final String WHITE_IMG_PATH = "res/whiteBishop.png";
	private static final String BLACK_IMG_PATH = "res/blackBishop.png";
	private static final double VALUE = 3.33;
	
	public Bishop(boolean color, Position position) {
		super(color, position, VALUE);
	}
	
	public Bishop(Bishop that) {
		super(that);
	}
	
	@Override
	public Bishop copy() {
		return new Bishop(this);
	}
	
	@Override
	public void draw(double x, double y, double width, double height) {
		if (getColor() == WHITE) {
			draw(WHITE_IMG_PATH, x, y, width, height);
		} else {
			draw(BLACK_IMG_PATH, x, y, width, height);
		}
	}

	@Override
	public ArrayList<Move> getLegalMoves(Board board) {
		return getDiagonalMoves(board, false);
	}
}
