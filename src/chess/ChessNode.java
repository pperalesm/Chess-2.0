package chess;

import java.util.ArrayList;

public class ChessNode {
	private ChessNode _parent;
	private ChessNode[] _children;
	private boolean _explored = false;
	private double _losses = 0;
	private double _wins = 0;
	
	public ChessNode() {}
	
	public ChessNode(ChessNode parent) {
		_parent = parent;
	}
	
	public void cleanTree() {
		for (int i = 0; i < _children.length; i += 1) {
			if (_children[i] != null) {
				if (_children[i]._explored) {
					_children[i].cleanTree();
				}
				if (_children[i].isUseless()) {
					_children[i]._parent = null;
					_children[i] = null;
				}
			}
		}
	}
	
	public void setMoves(ArrayList<Move> legalMoves) {
		_children = new ChessNode[legalMoves.size()];
		_explored = true;
	}
	
	public void update(boolean winner) {
		if (winner) {
			_wins += 1;
		} else {
			_losses += 1;
		}
	}
	
	public double getWinrate() {
		if (_wins + _losses == 0) {
			return 0.5;
		}
		return _wins/(_wins + _losses);
	}
	
	public int getMostWinrateMove() {
		int maxMove = -1;
		double maxWinrate = -1;
		for (int i = 0; i < _children.length; i += 1) {
			if (_children[i] != null && _children[i].getWinrate() >= maxWinrate) {
				maxMove = i;
				maxWinrate = _children[i].getWinrate();
			}
		}
		return maxMove;
	}
	
	public int getMostWinrateMoveFrom(ArrayList<Integer> candidatMoves) {
		int maxMove = -1;
		double maxWinrate = -1;
		for (Integer i: candidatMoves) {
			if (_children[i] != null && _children[i].getWinrate() >= maxWinrate) {
				maxMove = i;
				maxWinrate = _children[i].getWinrate();
			}
		}
		return maxMove;
	}
	
	public boolean isUseless() {
		return _wins + _losses < 2;
	}
	
	public ChessNode getChild(int index) {
		return _children[index];
	}
	
	public void setChild(int index, ChessNode child) {
		_children[index] = child;
	}
	
	public boolean isExplored() {
		return _explored;
	}
	
	public ChessNode getParent() {
		return _parent;
	}
	
	public ChessNode[] getChildren() {
		return _children;
	}
}
