package flickrPantailak;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class PantailaNagusia extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PantailaNagusia(){
		this.setTitle("FlickrBackup");
		this.setJMenuBar(new MenuBarra().createMenuBar());
				
		//this.setSize(500, 400);
		int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);
				
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new PantailaNagusia();
	}

}
