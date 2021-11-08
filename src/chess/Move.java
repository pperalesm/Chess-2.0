package chess;

public class Move implements Comparable<Move> {
	private boolean _castling = false;
	private Position _initial;
	private Position _final;
	
	public Move(int initialX, int initialY, int finalX, int finalY) {
		_initial = new Position(initialX, initialY);
		_final = new Position(finalX, finalY);
	}
	
	public Move(Position initialPosition, Position finalPosition) {
		_initial = initialPosition;
		_final = finalPosition;
	}
	
	public Move(Position initialPosition, Position finalPosition, boolean castling) {
		_initial = initialPosition;
		_final = finalPosition;
		_castling = castling;
	}
	
	public Position getInitial() {
		return _initial;
	}
	
	public Position getFinal() {
		return _final;
	}
	
	public boolean isCastling() {
		return _castling;
	}

	public int compareTo(Move that) {
		if (this._initial.getX() < that._initial.getX()) {
			return -1;
		}
		if (this._initial.getX() > that._initial.getX()) {
			return 1;
		}
		if (this._initial.getY() < that._initial.getY()) {
			return -1;
		}
		if (this._initial.getY() > that._initial.getY()) {
			return 1;
		}
		if (this._final.getX() < that._final.getX()) {
			return -1;
		}
		if (this._final.getX() > that._final.getX()) {
			return 1;
		}
		if (this._final.getY() < that._final.getY()) {
			return -1;
		}
		if (this._final.getY() > that._final.getY()) {
			return 1;
		}
		return 0;
	}
}
