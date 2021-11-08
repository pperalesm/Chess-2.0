package chess;

import java.util.ArrayList;

public class Rook extends Piece {
	private static final String WHITE_IMG_PATH = "res/whiteRook.png";
	private static final String BLACK_IMG_PATH = "res/blackRook.png";
	private static final double VALUE = 5.63;
	private boolean _moved = false;
	
	public Rook(boolean color, Position position) {
		super(color, position, VALUE);
	}
	
	public Rook(Rook that) {
		super(that);
		this._moved = that._moved;
	}
	
	@Override
	public Rook copy() {
		return new Rook(this);
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
		return getStraightMoves(board, false);
	}
	
	@Override
	public void moved() {
		_moved = true;
	}
	
	@Override
	public boolean hasMoved() {
		return _moved;
	}
}
