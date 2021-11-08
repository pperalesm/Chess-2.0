package chess;

public class Position {
	private int _x;
	private int _y;
	
	public Position(int x, int y) {
		_x = x;
		_y = y;
	}
	
	public int getX() {
		return _x;
	}
	
	public int getY() {
		return _y;
	}
	
	public Position add(int x, int y) {
		return new Position(_x + x, _y + y);
	}
	
	public boolean isValid() {
		return _x >= 0 && _x <= 7 && _y >= 0 && _y <= 7;
	}
}
