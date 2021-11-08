package chess;

import java.util.ArrayList;

public class King extends Piece {
	private static final String WHITE_IMG_PATH = "res/whiteKing.png";
	private static final String BLACK_IMG_PATH = "res/blackKing.png";
	public static final double VALUE = 1000;
	private boolean _moved = false;
	
	public King(boolean color, Position position) {
		super(color, position, VALUE);
	}
	
	public King(King that) {
		super(that);
		this._moved = that._moved;
	}
	
	@Override
	public King copy() {
		return new King(this);
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
		ArrayList<Move> legalMoves = getStraightMoves(board, true);
		legalMoves.addAll(getDiagonalMoves(board, true));
		Position finalPosition;
		if (!_moved) {
			finalPosition = getPosition().add(-2, 0);
			if (board.getPiece(finalPosition.add(1, 0)) == null && board.getPiece(finalPosition) == null &&
					board.getPiece(finalPosition.add(-1, 0)) != null && !board.getPiece(finalPosition.add(-1, 0)).hasMoved()) {
				legalMoves.add(new Move(getPosition(), finalPosition, true));
			}
			finalPosition = getPosition().add(2, 0);
			if (board.getPiece(finalPosition.add(-1, 0)) == null && board.getPiece(finalPosition) == null &&
					board.getPiece(finalPosition.add(1, 0)) == null && board.getPiece(finalPosition.add(2, 0)) != null &&
					!board.getPiece(finalPosition.add(2, 0)).hasMoved()) {
				legalMoves.add(new Move(getPosition(), finalPosition, true));
			}
		}
		return legalMoves;
	}
	
	@Override
	public void moved() {
		_moved = true;
	}
}
