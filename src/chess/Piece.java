package chess;

import java.util.ArrayList;

public abstract class Piece extends GUIElement {
	public static final boolean WHITE = false;
	public static final boolean BLACK = true;
	private boolean _color;
	private Position _position;
	private double _value;
	
	public Piece(boolean color, Position position, double value) {
		_color = color;
		_position = position;
		_value = value;
	}
	
	public Piece(Piece that) {
		super(that);
		this._color = that._color;
		this._position = that._position;
		this._value = that._value;
	}
	
	public abstract Piece copy();
	
	public abstract ArrayList<Move> getLegalMoves(Board board);
	
	public ArrayList<Move> getDiagonalMoves(Board board, boolean singleStep) {
		ArrayList<Move> diagonalMoves = new ArrayList<>();
		Position finalPosition;
		for (int i = -1; i <= 1; i += 2) {
			for (int j = -1; j <= 1; j += 2) {
				finalPosition = getPosition().add(i, j);
				while (finalPosition.isValid()) {
					if (!(board.getPiece(finalPosition) != null && board.getPiece(finalPosition).getColor() == getColor())) {
						diagonalMoves.add(new Move(getPosition(), finalPosition));
					}
					if (singleStep || board.getPiece(finalPosition) != null) {
						break;
					}
					finalPosition = finalPosition.add(i, j);
				}
			}
		}
		return diagonalMoves;
	}
	
	public ArrayList<Move> getStraightMoves(Board board, boolean singleStep) {
		ArrayList<Move> straightMoves = new ArrayList<>();
		Position finalPosition;
		for (int i = -1; i <= 1; i += 2) {
			finalPosition = getPosition().add(i, 0);
			while (finalPosition.isValid()) {
				if (!(board.getPiece(finalPosition) != null && board.getPiece(finalPosition).getColor() == getColor())) {
					straightMoves.add(new Move(getPosition(), finalPosition));
				}
				if (singleStep || board.getPiece(finalPosition) != null) {
					break;
				}
				finalPosition = finalPosition.add(i, 0);
			}
			finalPosition = getPosition().add(0, i);
			while (finalPosition.isValid()) {
				if (!(board.getPiece(finalPosition) != null && board.getPiece(finalPosition).getColor() == getColor())) {
					straightMoves.add(new Move(getPosition(), finalPosition));
				}
				if (singleStep || board.getPiece(finalPosition) != null) {
					break;
				}
				finalPosition = finalPosition.add(0, i);
			}
		}
		return straightMoves;
	}
	
	public boolean getColor() {
		return _color;
	}
	
	public Position getPosition() {
		return _position;
	}
	
	public void setPosition(Position position) {
		_position = position;
	}
	
	public void moved() {}
	
	public boolean hasMoved() {
		return true;
	}
	
	public double getValue() {
		return _value;
	}
}
