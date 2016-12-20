package sesioPantailak_UI;

import java.awt.*;
import java.util.Vector;
import javax.swing.*;

import pantailak_UI.PantailaNagusia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SesioaItxiPantaila extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private String erabiltzaile;
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
	
	public SesioaItxiPantaila(String email) {
		super(new BorderLayout());
		this.erabiltzaile = email;
		
		pantailaNagusia = new JFrame("FlickrBackup");
		
		picture = new JLabel(new ImageIcon(getClass().getResource("images/FlickrLogo.jpg")));
		
		hizkuntza = new JPanel();
		elementuak = new Vector<String>();
		elementuak.add("Euskara");
		elementuak.add("English");
		elementuak.add("Español");
		hizkuntzak = new JComboBox<String>(elementuak);
		
		south = new JPanel();
		goikoa = new JPanel();
		goikoa.setBackground(Color.LIGHT_GRAY);
		behekoa = new JPanel();
		behekoa.setBackground(Color.LIGHT_GRAY);
		
		irtenZara = new JLabel("Zure kontutik irten zara.");
		berriroSartu = new JLabel("Berriro sartu nahi?");
		sartuBotoia = new JButton("Sartu");
		
		sartuBotoia.addActionListener(this);

		goikoPanela();
		erdikoPanela();
		behekoPanela();
	}
	
	public void goikoPanela() {
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
		goikoa.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		behekoa.setLayout(new FlowLayout());
		behekoa.add(berriroSartu);
		behekoa.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));
		south.setBorder(BorderFactory.createEmptyBorder(10,100,50,100));
		south.add(goikoa);
		south.add(behekoa);
		south.add(sartuBotoia);
		
		pantailaNagusia.getContentPane().add(south, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e) {
		berriroSartu();
	}
	
	public void berriroSartu() {
		pantailaNagusia.dispose();
		PantailaNagusia frameNagusia = new PantailaNagusia(erabiltzaile);
		frameNagusia.eraikiFrame();
		frameNagusia.setupPropertiesLortu();
	}
	
	public void panelaEraikitzen() {		
		pantailaNagusia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pantailaNagusia.pack();
		pantailaNagusia.setLocationRelativeTo(null);
		pantailaNagusia.setVisible(true);
	}
	
}