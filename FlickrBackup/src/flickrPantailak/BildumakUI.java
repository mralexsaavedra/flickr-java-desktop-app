package flickrPantailak;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import flickrJava.Bildumak;

public class BildumakUI extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BildumakUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		lortuBildumak();
	}

	public void lortuBildumak() {
		Bildumak t;
		try {
			t = new Bildumak();
			List<String[]> emaitzak = t.showPhotosets();
			for (String[] bilduma : emaitzak) {
				add(new JLabel("Bilduma: " + bilduma[0] + "\t - Deskripzioa: " + bilduma[1] + "\t - ArgazkiCount: " + bilduma[2]));
			}
			add(new JLabel("-------------------------------------------------------------------------"));
			add(new JLabel("Bildumak guztira: " + String.valueOf(t.blidumenKontaketa())));
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
		
		frame.getContentPane().add(bUI, BorderLayout.CENTER);
	}
	
	
}
