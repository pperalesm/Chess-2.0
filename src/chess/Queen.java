package chess;

import java.util.ArrayList;

public class Queen extends Piece {
	private static final String WHITE_IMG_PATH = "res/whiteQueen.png";
	private static final String BLACK_IMG_PATH = "res/blackQueen.png";
	private static final double VALUE = 9.5;
	
	public Queen(boolean color, Position position) {
		super(color, position, VALUE);
	}
	
	public Queen(Queen that) {
		super(that);
	}
	
	@Override
	public Queen copy() {
		return new Queen(this);
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
		ArrayList<Move> legalMoves = getStraightMoves(board, false);
		legalMoves.addAll(getDiagonalMoves(board, false));
		return legalMoves;
	}
}
