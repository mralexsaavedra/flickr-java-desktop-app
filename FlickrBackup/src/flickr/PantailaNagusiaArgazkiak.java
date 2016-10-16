package flickr;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;

public class PantailaNagusiaArgazkiak extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JFrame pantailaNagusia;
	
	private Vector<String> hizkuntzElementuak = new Vector<String>();
	private JComboBox<String> hizkuntzak;
	private JButton irtenBotoia;
	private JPanel hizkuntzaEtaIrten;
	private JButton hasieraraBotoia;
	private JPanel hasieraraPanel;
	private JPanel goikoPanela;
	
	private Vector<String> bildumaElementuak = new Vector<String>();
	private JComboBox<String> bildumak;
	private JButton argazkiakBotoia;
	private JPanel ezkerrekoPanela;
	
	private JButton igoBotoia;
	private JPanel erdikoPanela;
	
	private JTextField bilatuText;
	private JButton bilatuBotoia;
	private JPanel eskumakoPanela;
	
	private JPanel behekoPanela;
	private final int LUZERA = 20;
	
	public PantailaNagusiaArgazkiak() {
		super(new BorderLayout());
		pantailaNagusia = new JFrame("FlickrBackup");
		iparraldePanela();
		mendebaldePanela();
		zentroPanela();
		ekialdePanela();
		hegoaldePanela();
	}
	
	public void iparraldePanela() {
		goikoPanela = new JPanel();
		
		hizkuntzaEtaIrten = new JPanel();
		hasieraraPanel = new JPanel();
		
		hizkuntzElementuak.add("Euskara");
		hizkuntzElementuak.add("English");
		hizkuntzElementuak.add("Español");
		hizkuntzak = new JComboBox<String>(hizkuntzElementuak);
		irtenBotoia = new JButton("Irten");
		hizkuntzaEtaIrten.setLayout(new BoxLayout(hizkuntzaEtaIrten, BoxLayout.X_AXIS));
		hizkuntzaEtaIrten.add(hizkuntzak);
		hizkuntzaEtaIrten.add(irtenBotoia);
		
		hasieraraBotoia = new JButton("Hasierara bueltatu");
		hasieraraPanel.setLayout(new BoxLayout(hasieraraPanel, BoxLayout.X_AXIS));
		hasieraraPanel.add(hasieraraBotoia);
		
		goikoPanela.setLayout(new BoxLayout(goikoPanela, BoxLayout.Y_AXIS));
		goikoPanela.add(hizkuntzaEtaIrten);
		goikoPanela.add(hasieraraPanel);
		pantailaNagusia.getContentPane().add(goikoPanela, BorderLayout.NORTH);
	}
	
	public void mendebaldePanela() {
		ezkerrekoPanela = new JPanel();		
		bildumaElementuak.add("Lehenengo bilduma");
		bildumaElementuak.add("Bigarren bilduma");
		bildumak = new JComboBox<String>(bildumaElementuak);
		argazkiakBotoia = new JButton("Argazkiak");
		ezkerrekoPanela.setLayout(new BoxLayout(ezkerrekoPanela, BoxLayout.X_AXIS));
		ezkerrekoPanela.add(bildumak);
		ezkerrekoPanela.add(argazkiakBotoia);
		pantailaNagusia.getContentPane().add(ezkerrekoPanela, BorderLayout.WEST);
	}
	
	public void zentroPanela() {
		erdikoPanela = new JPanel();
		igoBotoia = new JButton("Argazkiak igo");
		erdikoPanela.setLayout(new BoxLayout(erdikoPanela, BoxLayout.X_AXIS));
		erdikoPanela.add(igoBotoia);
		pantailaNagusia.getContentPane().add(erdikoPanela, BorderLayout.CENTER);
	}
	
	public void ekialdePanela() {
		eskumakoPanela = new JPanel();
		bilatuText = new JTextField(LUZERA);
		bilatuBotoia = new JButton("Bilatu");
		eskumakoPanela.setLayout(new BoxLayout(eskumakoPanela, BoxLayout.X_AXIS));
		eskumakoPanela.add(bilatuText);
		eskumakoPanela.add(bilatuBotoia);
		pantailaNagusia.getContentPane().add(eskumakoPanela, BorderLayout.EAST);
	}
	
	public void hegoaldePanela() {
		behekoPanela = new JPanel();
		pantailaNagusia.getContentPane().add(behekoPanela, BorderLayout.SOUTH);
	}
	
	public void panelaEraikitzen() {		
		pantailaNagusia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pantailaNagusia.pack();
		pantailaNagusia.setVisible(true);
	}

	public static void main(String[] args) {		 
		PantailaNagusiaArgazkiak n = new PantailaNagusiaArgazkiak();
		n.panelaEraikitzen();
	}
}