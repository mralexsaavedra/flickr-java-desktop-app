package flickrPantailak;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.*;

public class SesioaHasiPantaila extends JPanel implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame pantailaNagusia;
	private Vector<String> elementuak;
	private JComboBox<String> hizkuntzak;
	private JPanel hizkuntza;
	private JLabel picture;
	private JPanel south;
	private JPanel goikoa;
	private JPanel behekoa;
	private JLabel emailLabel;
	private JTextField emailText;
	private JLabel pasahitzaLabel;
	private JPasswordField pasahitzaText;
	private JButton sartuBotoia;
	private ArrayList<String> datuak;
	private final int TAMAINA = 15;

	public SesioaHasiPantaila() {
		super(new BorderLayout());
		
		pantailaNagusia = new JFrame("FlickrBackup");
		
		picture = new JLabel(new ImageIcon(getClass().getResource("images/FlickrLogo.jpg")));
		
		datuak = new ArrayList<String>();
		
		hizkuntza = new JPanel();
		elementuak = new Vector<String>();
		elementuak.add("Euskara");
		elementuak.add("English");
		elementuak.add("Español");
		hizkuntzak = new JComboBox<String>(elementuak);
		
		goikoa = new JPanel();
		goikoa.setBackground(Color.LIGHT_GRAY);
		behekoa = new JPanel();
		behekoa.setBackground(Color.LIGHT_GRAY);
		south = new JPanel();
		emailLabel = new JLabel("Email:");
		emailText = new JTextField(TAMAINA);
		pasahitzaLabel = new JLabel("Pasahitza:");
		pasahitzaText = new JPasswordField(TAMAINA);
		
		sartuBotoia = new JButton("Sartu");
				
		sartuBotoia.addActionListener(this);
		emailText.addKeyListener(this);
		pasahitzaText.addKeyListener(this);
		
		goikoPanela();
		erdikoPanela();
		behekoPanela();
	}

	public void goikoPanela() {
		hizkuntza.setLayout(new FlowLayout());
		hizkuntza.add(hizkuntzak);
		hizkuntza.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pantailaNagusia.getContentPane().add(hizkuntza, BorderLayout.NORTH);
	}

	public void erdikoPanela() {
		pantailaNagusia.getContentPane().add(picture, BorderLayout.CENTER);
	}

	public void behekoPanela() {
		goikoa.setLayout(new FlowLayout());
		goikoa.add(emailLabel);
		goikoa.add(emailText);
		goikoa.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

		behekoa.setLayout(new FlowLayout());
		behekoa.add(pasahitzaLabel);
		behekoa.add(pasahitzaText);
		behekoa.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

		south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));
		south.setBorder(BorderFactory.createEmptyBorder(10, 100, 50, 100));
		south.add(goikoa);
		south.add(behekoa);
		south.add(sartuBotoia);
		pantailaNagusia.getContentPane().add(south, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		berifikazioa();
	}

	public void fitxategienDatuakLortu() {
		File fitxategia = new File("src/fitxategia.txt");
		try {
			BufferedReader bf = new BufferedReader(new FileReader(fitxategia));
			String lerroa;
			while ((lerroa = bf.readLine()) != null) {
				datuak.add(lerroa.trim());
			}
			bf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void berifikazioa() {
		this.fitxategienDatuakLortu();
		
		if (emailText.getText().equals(datuak.get(0))
				&& Arrays.equals(pasahitzaText.getPassword(), datuak.get(1).toCharArray())) {
			pantailaNagusia.dispose();
			PantailaNagusia nagusia = new PantailaNagusia();
			nagusia.eraikiFrame();
		} else {
			JOptionPane.showMessageDialog(pantailaNagusia, "Datuak txarto daude", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void panelaEraikitzen() {
		pantailaNagusia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pantailaNagusia.pack();
		pantailaNagusia.setLocationRelativeTo(null);
		pantailaNagusia.setVisible(true);
	}

	public static void main(String[] args) {
		SesioaHasiPantaila h = new SesioaHasiPantaila();
		h.panelaEraikitzen();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			berifikazioa();
	}

	public void keyReleased(KeyEvent e) {
	}

}