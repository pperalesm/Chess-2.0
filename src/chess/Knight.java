package chess;

import java.util.ArrayList;

public class Knight extends Piece {
	private static final String WHITE_IMG_PATH = "res/whiteKnight.png";
	private static final String BLACK_IMG_PATH = "res/blackKnight.png";
	private static final double VALUE = 3.05;
	
	public Knight(boolean color, Position position) {
		super(color, position, VALUE);
	}
	
	public Knight(Knight that) {
		super(that);
	}
	
	@Override
	public Knight copy() {
		return new Knight(this);
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
		for (int i = -1; i <= 1; i += 2) {
			for (int j = -2; j <= 2; j += 4) {
				finalPosition = getPosition().add(j, i);
				if (finalPosition.isValid() && !(board.getPiece(finalPosition) != null && board.getPiece(finalPosition).getColor() == getColor())) {
					legalMoves.add(new Move(getPosition(), finalPosition));
				}
				finalPosition = getPosition().add(i, j);
				if (finalPosition.isValid() && !(board.getPiece(finalPosition) != null && board.getPiece(finalPosition).getColor() == getColor())) {
					legalMoves.add(new Move(getPosition(), finalPosition));
				}
			}
		}
		return legalMoves;
	}
}
