package flickr;

import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SesioaItxiPantaila extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JFrame pantailaNagusia;
	private Vector<String> elementuak;
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
		hizkuntza = new JPanel();
		elementuak = new Vector<String>();
		hizkuntzak = new JComboBox<String>(elementuak);
		picture = new JLabel(new ImageIcon(getClass().getResource("images/FlickrLogo.jpg")));
		south = new JPanel();
		goikoa = new JPanel();
		behekoa = new JPanel();
		irtenZara = new JLabel("Zure kontutik irten zara.");
		berriroSartu = new JLabel("Berriro sartu nahi?");
		sartuBotoia = new JButton("Sartu");
		sartuBotoia.addActionListener(this);

		goikoPanela();
		erdikoPanela();
		behekoPanela();
	}
	
	public void goikoPanela() {
		elementuak.add("Euskara");
		elementuak.add("English");
		elementuak.add("Español");
		hizkuntza.setLayout(new FlowLayout());
		hizkuntza.add(hizkuntzak);
		hizkuntza.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		pantailaNagusia.getContentPane().add(hizkuntza, BorderLayout.NORTH);
	}
	
	public void erdikoPanela() {
		pantailaNagusia.getContentPane().add(picture, BorderLayout.CENTER);
	}
	
	public void behekoPanela() {
		goikoa.setLayout(new FlowLayout());
		goikoa.add(irtenZara);
		goikoa.setBorder(BorderFactory.createEmptyBorder(10,10,5,10));

		behekoa.setLayout(new FlowLayout());
		behekoa.add(berriroSartu);
		behekoa.setBorder(BorderFactory.createEmptyBorder(5,10,10,10));

		south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));
		south.add(goikoa);
		south.add(behekoa);
		south.add(sartuBotoia);
		south.setBorder(BorderFactory.createEmptyBorder(10,10,50,10));
			
		pantailaNagusia.getContentPane().add(south, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e) {
		berriroSartu();
	}
	
	public void berriroSartu() {
		pantailaNagusia.dispose();
		PantailaNagusia sartzeko = new PantailaNagusia();
		sartzeko.panelaEraikitzen();
	}
	
	public void panelaEraikitzen() {		
		pantailaNagusia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pantailaNagusia.pack();
		pantailaNagusia.setLocationRelativeTo(null);
		pantailaNagusia.setVisible(true);
	}
	
	public static void main(String[] args) {		 
		SesioaItxiPantaila s = new SesioaItxiPantaila();
		s.panelaEraikitzen();
	}
}