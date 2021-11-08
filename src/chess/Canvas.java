package chess;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel {
	private static Canvas _instance;
	private HashMap<String, Image> _loadedImages = new HashMap<>();
	private ArrayList<GUIInfo> _drawnImages = new ArrayList<>();
	private JFrame _frame = new JFrame();
	private boolean _blocked = false;
	
	private Canvas() {
		_frame.add(this);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 3/4,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 3/4);
		_frame.setLocationRelativeTo(null);
		_frame.setTitle("Chess");
		_frame.setVisible(true);
	}
	
	public static Canvas getInstance() {
		if (_instance == null) {
			_instance = new Canvas();
		}
		return _instance;
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        _blocked = true;
        for (GUIInfo guiInfo: _drawnImages) {
            g.drawImage(_loadedImages.get(guiInfo.getImagePath()), (int) (guiInfo.getX() * getWidth()),
            		(int) (guiInfo.getY() * getHeight()), (int) (guiInfo.getWidth() * getWidth()),
            		(int) (guiInfo.getHeight() * getHeight()), this);
        }
        _blocked = false;
    }
	
	public void update() {
		repaint();
	}
	
	public void update(double x, double y, double width, double height) {
		repaint(new Rectangle((int) (x * getWidth()), (int) (y * getHeight()),
				(int) (width * getWidth()), (int) (height * getHeight())));
	}
	
	public void addImage(GUIInfo guiInfo) {
		if (!_loadedImages.containsKey(guiInfo.getImagePath())) {
    		_loadedImages.put(guiInfo.getImagePath(), Toolkit.getDefaultToolkit().getImage(guiInfo.getImagePath()));
    	}
		while(_blocked) {}
		_drawnImages.add(guiInfo);
	}
	
	public void removeImage(GUIInfo guiInfo) {
		while(_blocked) {}
		_drawnImages.remove(guiInfo);
	}
	
	public void close() {
		_frame.dispose();
		_instance = null;
	}
	
	public void clear() {
		while(_blocked) {}
		_drawnImages.clear();
	}
	
	public boolean isBlocked() {
		return _blocked;
	}
}
