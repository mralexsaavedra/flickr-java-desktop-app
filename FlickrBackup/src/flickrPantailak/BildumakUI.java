package flickrPantailak;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import flickrJava.BildumakPantailaratu;

public class BildumakUI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	public BildumakUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	public void lortuBildumak() {
		BildumakPantailaratu t;
		try {
			t = new BildumakPantailaratu();
			List<String[]> emaitzak = t.showPhotosets();
			for (String[] bilduma : emaitzak) {
				add(new JLabel("Bilduma: " + bilduma[0] + "\t - Deskripzioa: " + bilduma[1]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
        frame.setVisible(true);
		
		BildumakUI bUI = new BildumakUI();
		bUI.lortuBildumak();	
		
		bUI.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		frame.getContentPane().add(bUI, BorderLayout.CENTER);
	}
	
	
}
