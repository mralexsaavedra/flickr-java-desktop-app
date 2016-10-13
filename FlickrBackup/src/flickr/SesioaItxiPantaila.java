package flickr;

import java.awt.*;
import java.util.Vector;
import javax.swing.*;

public class SesioaItxiPantaila extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JFrame pantailaNagusia;
	private Vector<String> elementuak = new Vector<String>();
	private JComboBox<String> hizkuntzak;
	private JPanel hizkuntza;
	private JLabel picture;
	private JPanel south;
	private JPanel goikoa;
	private JPanel behekoa;
	private JLabel irtenZara;
	private JLabel berriroSartu;
	private JButton sartuBotoia;
	
	public SesioaItxiPantaila() {
		super(new BorderLayout());
		pantailaNagusia = new JFrame("FlickrBackup");
		goikoPanela();
		erdikoPanela();
		behekoPanela();
	}
	
	public void goikoPanela() {
		hizkuntza = new JPanel();
		elementuak.add("Euskara");
		elementuak.add("English");
		elementuak.add("Español");
		hizkuntzak = new JComboBox<String>(elementuak);
		hizkuntza.setLayout(new FlowLayout());
		hizkuntza.add(hizkuntzak);
		pantailaNagusia.getContentPane().add(hizkuntza, BorderLayout.NORTH);
	}
	
	public void erdikoPanela() {
		picture = new JLabel(new ImageIcon(getClass().getResource("images/FlickrLogo.jpg")));
		pantailaNagusia.getContentPane().add(picture, BorderLayout.CENTER);
	}
	
	public void behekoPanela() {
		south = new JPanel();
		goikoa = new JPanel();
		behekoa = new JPanel();

		irtenZara = new JLabel("Zure kontutik irten zara.");
		berriroSartu = new JLabel("Berriro sartu nahi?");
		sartuBotoia = new JButton("Sartu");

		goikoa.setLayout(new BoxLayout(goikoa, BoxLayout.X_AXIS));
		goikoa.add(irtenZara);

		behekoa.setLayout(new BoxLayout(behekoa, BoxLayout.X_AXIS));
		behekoa.add(berriroSartu);

		south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));
		south.add(goikoa);
		south.add(behekoa);
		south.add(sartuBotoia);
		pantailaNagusia.getContentPane().add(south, BorderLayout.SOUTH);
	}
	
	public void panelaEraikitzen() {		
		pantailaNagusia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pantailaNagusia.pack();
		pantailaNagusia.setVisible(true);
	}
	
	public static void main(String[] args) {		 
		HasierakoPantaila h = new HasierakoPantaila();
		h.panelaEraikitzen();
	}
}