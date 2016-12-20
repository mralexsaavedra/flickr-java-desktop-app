package flickrAuth;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Fitxategia extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFileChooser fc = new JFileChooser();

	public Fitxategia() {

	}

	public String fitxategiaSortu() {
		String text = "apiKey = \n" + "email = \n" + "secret = \n" + "token = \n" + "tokensecret = \n" + "nsid = \n"
				+ "username = ";

		fc.showSaveDialog(this);
		File gorde = fc.getSelectedFile();
		FileWriter idatzi;
		try {
			idatzi = new FileWriter(gorde, true);
			idatzi.write(text);
			idatzi.close();
			JOptionPane.showMessageDialog(null, "Fitxategia ondo gorde egin da", "Informazioa", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fitxategia ezin da gorde", "WARNING", JOptionPane.WARNING_MESSAGE);
		}
		return gorde.getAbsolutePath();
	}

}
