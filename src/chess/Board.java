package chess;

import java.util.ArrayList;
import java.util.Comparator;

public class Board extends GUIElement{
	private static final String IMG_PATH = "res/board.gif";
	private Piece[][] _board = new Piece[8][8];
	private ArrayList<Piece> _whitePieces = new ArrayList<>();
	private ArrayList<Piece> _blackPieces = new ArrayList<>();
	
	public Board() {
		Piece piece;
		for (int i = 0; i < 8; i += 1) {
			piece = new Pawn(Piece.WHITE, new Position(i, 6));
			_board[i][6] = piece;
			_whitePieces.add(piece);
			piece = new Pawn(Piece.BLACK, new Position(i, 1));
			_board[i][1] = piece;
			_blackPieces.add(piece);
		}
		for (int i = 0; i < 8; i += 7) {
			piece = new Rook(Piece.WHITE, new Position(i, 7));
			_board[i][7] = piece;
			_whitePieces.add(piece);
			piece = new Rook(Piece.BLACK, new Position(i, 0));
			_board[i][0] = piece;
			_blackPieces.add(piece);
		}
		for (int i = 1; i < 8; i += 5) {
			piece = new Knight(Piece.WHITE, new Position(i, 7));
			_board[i][7] = piece;
			_whitePieces.add(piece);
			piece = new Knight(Piece.BLACK, new Position(i, 0));
			_board[i][0] = piece;
			_blackPieces.add(piece);
		}
		for (int i = 2; i < 8; i += 3) {
			piece = new Bishop(Piece.WHITE, new Position(i, 7));
			_board[i][7] = piece;
			_whitePieces.add(piece);
			piece = new Bishop(Piece.BLACK, new Position(i, 0));
			_board[i][0] = piece;
			_blackPieces.add(piece);
		}
		piece = new Queen(Piece.WHITE, new Position(4, 7));
		_board[4][7] = piece;
		_whitePieces.add(piece);
		piece = new Queen(Piece.BLACK, new Position(4, 0));
		_board[4][0] = piece;
		_blackPieces.add(piece);
		piece = new King(Piece.WHITE, new Position(3, 7));
		_board[3][7] = piece;
		_whitePieces.add(piece);
		piece = new King(Piece.BLACK, new Position(3, 0));
		_board[3][0] = piece;
		_blackPieces.add(piece);
	}
	
	public Board(Board that) {
		super(that);
		for (int row = 0; row < 8; row += 1) {
			for (int col = 0; col < 8; col += 1) {
				if (that._board[col][row] == null) {
					this._board[col][row] = null;
				} else {
					this._board[col][row] = that._board[col][row].copy();
					if (this._board[col][row].getColor() == Piece.WHITE) {
						_whitePieces.add(this._board[col][row]);
					} else {
						_blackPieces.add(this._board[col][row]);
					}
				}
			}
		}
	}
	
	@Override
	public void draw(double x, double y, double width, double height) {
		super.draw(IMG_PATH, x, y, width, height);
		for (int row = 0; row < 8; row += 1) {
			for (int col = 0; col < 8; col += 1) {
				if (_board[col][row] != null) {
					_board[col][row].draw(x + col*width*1/8, y + row*height*1/8, width*1/8, height*1/8);
				}
			}
		}
	}
	
	@Override
	public void erase() {
		super.erase();
		for (int row = 0; row < 8; row += 1) {
			for (int col = 0; col < 8; col += 1) {
				if (_board[col][row] != null) {
					_board[col][row].erase();
				}
			}
		}
	}
	
	@Override
	public void drawMove() {
		super.drawMove();
		for (int row = 0; row < 8; row += 1) {
			for (int col = 0; col < 8; col += 1) {
				if (_board[col][row] != null) {
					_board[col][row].drawMove();
				}
			}
		}
	}
	
	public Piece getPiece(Position position) {
		return _board[position.getX()][position.getY()];
	}
	
	public Piece movePiece(Move move) {
		int initialX = move.getInitial().getX();
		int initialY = move.getInitial().getY();
		int finalX = move.getFinal().getX();
		int finalY = move.getFinal().getY();
		Piece deadPiece = _board[finalX][finalY];
		if (deadPiece != null) {
			if (deadPiece.getColor() == Piece.WHITE) {
				_whitePieces.remove(deadPiece);
			} else {
				_blackPieces.remove(deadPiece);
			}
		}
		_board[initialX][initialY].setPosition(move.getFinal());
		_board[finalX][finalY] = _board[initialX][initialY];
		_board[initialX][initialY] = null;
		_board[finalX][finalY].moved();
		if (move.isCastling()) {
			if (finalX == 1) {
				movePiece(new Move(0, finalY, 2, finalY));
			} else {
				movePiece(new Move(7, finalY, 4, finalY));
			}
		}
		return deadPiece;
	}
	
	public void drawPieceMove(Move move) {
		Piece piece = _board[move.getFinal().getX()][move.getFinal().getY()];
		piece.setX(getX() + piece.getPosition().getX()*getWidth()*1/8);
		piece.setY(getY() + piece.getPosition().getY()*getHeight()*1/8);
		piece.drawMove();
	}
	
	public ArrayList<Move> getLegalMoves(boolean color) {
		ArrayList<Move> legalMoves = new ArrayList<>();
		if (color == Piece.WHITE) {
			for (Piece piece: _whitePieces) {
				legalMoves.addAll(piece.getLegalMoves(this));
			}
		} else {
			for (Piece piece: _blackPieces) {
				legalMoves.addAll(piece.getLegalMoves(this));
			}
		}
		legalMoves.sort(Comparator.naturalOrder());
		return legalMoves;
	}
	
	public String toString() {
		String result = "";
		for (int row = 0; row < 8; row += 1) {
			for (int col = 0; col < 8; col += 1) {
				if (_board[col][row] == null) {
					result += " ";
				} else {
					result += "o";
				}
			}
			result += "\n";
		}
		return result + "--------";
	}
}
