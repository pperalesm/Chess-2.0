package chess;

import java.util.ArrayList;

public class Game {
	private Player[] _players = new Player[2];
	private Board _board;
	private boolean _simulated;
	private ChessNode _currentNode;
	private ChessNode _rootNode;
	private boolean _turn;
	private boolean _active = true;
	private int _noKillCounter = 0;
	
	public Game(boolean simulated, Player white, Player black, ChessNode currentNode) {
		_turn = Piece.WHITE;
		_board = new Board();
		_players[0] = white;
		_players[1] = black;
		_simulated = simulated;
		_currentNode = currentNode;
		_rootNode = _currentNode;
		if (!_simulated) {
			_board.draw(0., 0., 1., 1.);
		}
	}
	
	public Game(Game that, Player white, Player black) {
		this._board = new Board(that._board);
		this._players[0] = white;
		this._players[1] = black;
		this._simulated = true;
		this._turn = that._turn;
		this._currentNode = that._currentNode;
		this._rootNode = this._currentNode;
	}
	
	public ArrayList<Move> getLegalMoves() {
		return _board.getLegalMoves(_turn);
	}
	
	public double playMove(int chosenMove, ArrayList<Move> legalMoves) {
		Piece deadPiece;
		double value = 0;
		if (!_currentNode.isExplored()) {
			_currentNode.setMoves(legalMoves);
		}
		if (_currentNode.getChild(chosenMove) == null) {
			_currentNode.setChild(chosenMove, new ChessNode(_currentNode));
		}
		_currentNode = _currentNode.getChild(chosenMove);
		deadPiece = _board.movePiece(legalMoves.get(chosenMove));
		if (!_simulated) {
			if (deadPiece != null) {
				deadPiece.erase();
			}
			_board.drawPieceMove(legalMoves.get(chosenMove));
			if (legalMoves.get(chosenMove).isCastling()) {
				if (legalMoves.get(chosenMove).getFinal().getX() == 1) {
					_board.drawPieceMove(new Move(0, legalMoves.get(chosenMove).getFinal().getY(), 2,
							legalMoves.get(chosenMove).getFinal().getY()));
				} else {
					_board.drawPieceMove(new Move(7, legalMoves.get(chosenMove).getFinal().getY(), 4,
							legalMoves.get(chosenMove).getFinal().getY()));
				}
			}
		}
		_turn = !_turn;
		if (deadPiece != null) {
			_noKillCounter = 0;
			if (deadPiece instanceof King) {
				_active = !_active;
			}
			value = deadPiece.getValue();
		} else {
			_noKillCounter += 1;
			if (_noKillCounter == 50) {
				_turn = !_turn;
				_active = !_active;
			}
		}
		return value;
	}
	
	public double playTurn() {
		ArrayList<Move> legalMoves;
		int chosenMove;
		legalMoves = _board.getLegalMoves(_turn);
		if (legalMoves.size() == 0) {
			_active = !_active;
			return 0;
		}
		if (_turn == Piece.WHITE) {
			chosenMove = _players[0].choose(this, legalMoves);
		} else {
			chosenMove = _players[1].choose(this, legalMoves);
		}
		if (chosenMove == -1) {
			return 0;
		}
		return playMove(chosenMove, legalMoves);
	}
	
	public boolean run() {
		while (_active) {
			playTurn();
		}
		boolean winner = true;
		while (_currentNode != _rootNode) {
			_currentNode.update(winner);
			_currentNode = _currentNode.getParent();
			winner = !winner;
		}
		if (!_simulated) {
			Canvas.getInstance().clear();
		}
		return !_turn;
	}
	
	public ChessNode getCurrentNode() {
		return _currentNode;
	}
	
	public boolean getTurn() {
		return _turn;
	}
}