package flickrPantailak;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import flickrJava.Argazkiak;

public class ArgazkiakUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArgazkiakUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
        frame.setSize(400, 300);
        ArgazkiakUI aUI = new ArgazkiakUI();
        aUI.lortuArgazkiak();
   		frame.getContentPane().add(aUI, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	public void lortuArgazkiak(){
		Argazkiak t;
		try {
			t = new Argazkiak();
			List<String[]> emaitzak = t.showPhotos();
			for (String[] argazkia : emaitzak) {
				add(new JLabel("ArgazkiTitle: " + argazkia[0]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
