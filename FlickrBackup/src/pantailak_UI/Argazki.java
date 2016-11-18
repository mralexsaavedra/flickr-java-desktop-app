package pantailak_UI;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Argazki extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x, y;
	private String path;

    public Argazki(JPanel jPanel, String file) {
        this.x = jPanel.getWidth();
        this.y = jPanel.getHeight();
        this.setSize(x, y);
        this.path = file;
    }

    public void paint(Graphics g) {
        ImageIcon Img = new ImageIcon(path);
        g.drawImage(Img.getImage(), 0, 0, x, y, null);
        System.out.println("draw");
    }    

}
