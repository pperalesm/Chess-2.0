package chess;

import java.util.ArrayList;

public class Pawn extends Piece {
	private static final String WHITE_IMG_PATH = "res/whitePawn.png";
	private static final String BLACK_IMG_PATH = "res/blackPawn.png";
	private static final double VALUE = 1;
	private boolean _moved = false;
	
	public Pawn(boolean color, Position position) {
		super(color, position, VALUE);
	}
	
	public Pawn(Pawn that) {
		super(that);
		this._moved = that._moved;
	}
	
	@Override
	public Pawn copy() {
		return new Pawn(this);
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
		ArrayList<Move> legalMoves = new ArrayList<>();
		Position finalPosition;
		if (getColor() == WHITE) {
			finalPosition = getPosition().add(0, -1);
			if (finalPosition.isValid() && board.getPiece(finalPosition) == null) {
				legalMoves.add(new Move(getPosition(), finalPosition));
				finalPosition = getPosition().add(0, -2);
				if (!_moved && finalPosition.isValid() && board.getPiece(finalPosition) == null) {
					legalMoves.add(new Move(getPosition(), finalPosition));
				}
			}
			finalPosition = getPosition().add(1, -1);
			if (finalPosition.isValid() && board.getPiece(finalPosition) != null && board.getPiece(finalPosition).getColor() != getColor()) {
				legalMoves.add(new Move(getPosition(), finalPosition));
			}
			finalPosition = getPosition().add(-1, -1);
			if (finalPosition.isValid() && board.getPiece(finalPosition) != null && board.getPiece(finalPosition).getColor() != getColor()) {
				legalMoves.add(new Move(getPosition(), finalPosition));
			}
		} else {
			finalPosition = getPosition().add(0, 1);
			if (finalPosition.isValid() && board.getPiece(finalPosition) == null) {
				legalMoves.add(new Move(getPosition(), finalPosition));
				finalPosition = getPosition().add(0, 2);
				if (!_moved && finalPosition.isValid() && board.getPiece(finalPosition) == null) {
					legalMoves.add(new Move(getPosition(), finalPosition));
				}
			}
			finalPosition = getPosition().add(1, 1);
			if (finalPosition.isValid() && board.getPiece(finalPosition) != null && board.getPiece(finalPosition).getColor() != getColor()) {
				legalMoves.add(new Move(getPosition(), finalPosition));
			}
			finalPosition = getPosition().add(-1, 1);
			if (finalPosition.isValid() && board.getPiece(finalPosition) != null && board.getPiece(finalPosition).getColor() != getColor()) {
				legalMoves.add(new Move(getPosition(), finalPosition));
			}
		}
		return legalMoves;
	}
	
	@Override
	public void moved() {
		_moved = true;
	}
}