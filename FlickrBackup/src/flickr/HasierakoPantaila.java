package flickr;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.*;

public class HasierakoPantaila extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame pantailaNagusia;
	private Vector<String> elementuak = new Vector<String>();
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

	public HasierakoPantaila() {
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
		hizkuntza.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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

		emailLabel = new JLabel("Email:");
		emailText = new JTextField(TAMAINA);
		pasahitzaLabel = new JLabel("Pasahitza:");
		pasahitzaText = new JPasswordField(TAMAINA);
		sartuBotoia = new JButton("Sartu");

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
		sartuBotoia.addActionListener(this);
		pantailaNagusia.getContentPane().add(south, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		berifikazioa();
	}
	
	public ArrayList<String> fitxategienDatuakLortu() {
		File fitxategia = new File("/Users/alexander/workspaceJava/FlickrBackup/FlickrBackup/src/fitxategia.txt");
		ArrayList<String> datuak = new ArrayList<String>();
		//int kont = 0;
		try {
			Scanner scan = new Scanner(fitxategia);
			while (scan.hasNext()) {
				String linea = scan.nextLine();
				datuak.add(linea.trim());
				//kont++;
			}
			
			 /*String [] datuenArray = new String[kont];

             for (int i=0; i<datuenArray.length; i++){
            	 datuenArray[i] = datuak.get(i);
                 System.out.println("Mostrando línea " + (i+1) + " del fichero: " + datuenArray[i]);
             }*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datuak;
	}
	
	public void berifikazioa() {
		System.out.println("Berifiazioan gaude");
		if (emailText.getText().equals(fitxategienDatuakLortu().get(0))){
			System.out.println("Email ondo");
			if (pasahitzaText.getText().equals(fitxategienDatuakLortu().get(1))){
				System.exit(0);
				//new PantailaNagusia();
				System.out.println("Email eta pasahitza ondo");
			}
			else{
				JOptionPane.showMessageDialog(pantailaNagusia, "Pasahitza txarto dago", "ERROR", JOptionPane.ERROR_MESSAGE);
				pasahitzaText.setText("");
			}
		}
		else{
			JOptionPane.showMessageDialog(pantailaNagusia, "Email txarto dago", "ERROR", JOptionPane.ERROR_MESSAGE);
			emailText.setText("");
		}
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