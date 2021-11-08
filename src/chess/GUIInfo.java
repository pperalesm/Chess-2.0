package chess;

public class GUIInfo {
	private String _imagePath;
	private double _x;
	private double _y;
	private double _lastX;
	private double _lastY;
	private double _width;
	private double _height;
	
	public GUIInfo(String imagePath, double x, double y, double width, double height) {
		_imagePath = imagePath;
		_x = x;
		_y = y;
		_lastX = x;
		_lastY = y;
		_width = width;
		_height = height;
	}
	
	public GUIInfo(GUIInfo that) {
		if (that != null) {
			this._imagePath = that._imagePath;
			this._x = that._x;
			this._y = that._y;
			this._lastX = that._lastX;
			this._lastY = that._lastY;
			this._width = that._width;
			this._width = that._width;
		}
	}
	
	
	public String getImagePath() {
		return _imagePath;
	}
	
	public double getX() {
		return _x;
	}
	
	public double getY() {
		return _y;
	}
	
	public double getLastX() {
		return _lastX;
	}
	
	public double getLastY() {
		return _lastY;
	}
	
	public double getWidth() {
		return _width;
	}
	
	public double getHeight() {
		return _height;
	}
	
	public void setX(double x) {
		_lastX = _x;
		_x = x;
	}
	
	public void setY(double y) {
		_lastY = _y;
		_y = y;
	}
}
