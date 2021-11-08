package chess;

public abstract class GUIElement {
	private GUIInfo _guiInfo;
	
	public GUIElement() {}
	
	public GUIElement(GUIElement that) {
		this._guiInfo = new GUIInfo(that._guiInfo);
	}
	
	public void draw(String imagePath, double x, double y, double width, double height) {
		_guiInfo = new GUIInfo(imagePath, x, y, width, height);
		Canvas.getInstance().addImage(_guiInfo);
		Canvas.getInstance().update(_guiInfo.getX(), _guiInfo.getY(), _guiInfo.getWidth(), _guiInfo.getHeight());
	}
	
	public void erase() {
		Canvas.getInstance().removeImage(_guiInfo);
	}
	
	public void drawMove() {
		Canvas.getInstance().update(_guiInfo.getLastX(), _guiInfo.getLastY(), _guiInfo.getWidth(), _guiInfo.getHeight());
		Canvas.getInstance().update(_guiInfo.getX(), _guiInfo.getY(), _guiInfo.getWidth(), _guiInfo.getHeight());
	}
	
	public abstract void draw(double x, double y, double width, double height);
	
	public double getX() {
		return _guiInfo.getX();
	}
	
	public double getY() {
		return _guiInfo.getY();
	}
	
	public double getWidth() {
		return _guiInfo.getWidth();
	}
	
	public double getHeight() {
		return _guiInfo.getHeight();
	}
	
	public void setX(double x) {
		_guiInfo.setX(x);
	}
	
	public void setY(double y) {
		_guiInfo.setY(y);
	}
}
