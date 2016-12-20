package sesioPantailak_UI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Vector;
import javax.swing.*;

import flickrJava.MD5;
import kudeatzaileak.Kudeatzailea;
import pantailak_UI.PantailaNagusia;

public class SesioaHasiPantaila extends JPanel implements KeyListener {

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
	private final int TAMAINA = 15;

	public SesioaHasiPantaila() {
		super(new BorderLayout());
		
		
		pantailaNagusia = new JFrame("FlickrBackup");
		
		picture = new JLabel(new ImageIcon(getClass().getResource("images/FlickrLogo.jpg")));
				
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
				
		sartuBotoia.addActionListener(actionListener -> {
			try {
				this.berifikazioa();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		emailText.addKeyListener(this);
		pasahitzaText.addKeyListener(this);
		
		goikoPanela();
		pantailaNagusia.getContentPane().add(picture, BorderLayout.CENTER);
		behekoPanela();
		
	}

	public void goikoPanela() {
		hizkuntza.setLayout(new FlowLayout());
		hizkuntza.add(hizkuntzak);
		hizkuntza.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pantailaNagusia.getContentPane().add(hizkuntza, BorderLayout.NORTH);
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
	
	public void panelaEraikitzen() {
		pantailaNagusia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pantailaNagusia.pack();
		pantailaNagusia.setLocationRelativeTo(null);
		pantailaNagusia.setVisible(true);
	}

	public void berifikazioa() throws Exception {
		List<String[]> emaitzak = Kudeatzailea.getInstantzia().getErabiltzaileak();
		char[] pass = this.pasahitzaText.getPassword();
		String passString = new String(pass);
		
		MD5 md5 = new MD5();

		if (emailText.getText().isEmpty() || pass.length==0){
			JOptionPane.showMessageDialog(pantailaNagusia, "Datuak txarto daude", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		else{
			for (String[] erabiltzaile : emaitzak){
				if (emailText.getText().equals(erabiltzaile[0])) {
					if (md5.MD5Hashing(passString).equals(erabiltzaile[1])){
						pantailaNagusia.dispose();
						PantailaNagusia nagusia = new PantailaNagusia(this.emailText.getText());
						nagusia.eraikiFrame();
						nagusia.setupPropertiesLortu();
					}	
				}	
				else {
					//JOptionPane.showMessageDialog(pantailaNagusia, "Datuak txarto daude", "ERROR", JOptionPane.ERROR_MESSAGE);
					Kudeatzailea.getInstantzia().erabiltzaileaGehitu(emailText.getText(), md5.MD5Hashing(passString));
					pantailaNagusia.dispose();
					PantailaNagusia nagusia = new PantailaNagusia(this.emailText.getText());
					nagusia.eraikiFrame();
					nagusia.setupPropertiesLortu();
				}
			}
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			try {
				berifikazioa();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	}

	public void keyReleased(KeyEvent e) {
	}

}