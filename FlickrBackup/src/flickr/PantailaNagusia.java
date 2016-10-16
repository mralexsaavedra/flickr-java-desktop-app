package flickr;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;

public class PantailaNagusia extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JFrame pantailaNagusia;
	
	private Vector<String> hizkuntzElementuak = new Vector<String>();
	private JComboBox<String> hizkuntzak;
	private JButton irtenBotoia;
	private JPanel hizkuntzaEtaIrten;
	private JButton argazkiakJaitsiBotoia;
	private JPanel argazkiakJaitsiPanel;
	private JButton argazkiakIgoBotoia;
	private JPanel argazkiakIgoPanel;
	private JPanel goikoPanela;
	
	private Vector<String> bildumaElementuak = new Vector<String>();
	private JComboBox<String> bildumak;
	private JButton argazkiakBotoia;
	private JPanel ezkerrekoPanela;
	
	private JTextField bilatuText;
	private JButton bilatuBotoia;
	private JPanel eskumakoPanela;
	
	private JPanel behekoPanela;
	private final int LUZERA = 20;
	
	public PantailaNagusia() {
		super(new BorderLayout());
		pantailaNagusia = new JFrame("FlickrBackup");
		iparraldePanela();
		mendebaldePanela();
		ekialdePanela();
		hegoaldePanela();
	}
	
	public void iparraldePanela() {
		goikoPanela = new JPanel();
		
		hizkuntzaEtaIrten = new JPanel();
		argazkiakJaitsiPanel = new JPanel();
		argazkiakIgoPanel = new JPanel();
		
		hizkuntzElementuak.add("Euskara");
		hizkuntzElementuak.add("English");
		hizkuntzElementuak.add("Español");
		hizkuntzak = new JComboBox<String>(hizkuntzElementuak);
		irtenBotoia = new JButton("Irten");
		hizkuntzaEtaIrten.setLayout(new BoxLayout(hizkuntzaEtaIrten, BoxLayout.X_AXIS));
		hizkuntzaEtaIrten.add(hizkuntzak);
		hizkuntzaEtaIrten.add(irtenBotoia);
		hizkuntzaEtaIrten.setBorder(BorderFactory.createEmptyBorder(10,1200,10,10));
		
		argazkiakJaitsiBotoia = new JButton("Flickr-etik argazkiak jaitsi");
		argazkiakJaitsiPanel.setLayout(new BoxLayout(argazkiakJaitsiPanel, BoxLayout.X_AXIS));
		argazkiakJaitsiPanel.add(argazkiakJaitsiBotoia);
		argazkiakJaitsiPanel.setBorder(BorderFactory.createEmptyBorder(10,10,5,1100));
		
		argazkiakIgoBotoia = new JButton("Flickr-era argazkiak igo");
		argazkiakIgoPanel.setLayout(new BoxLayout(argazkiakIgoPanel, BoxLayout.X_AXIS));
		argazkiakIgoPanel.add(argazkiakIgoBotoia);
		argazkiakIgoPanel.setBorder(BorderFactory.createEmptyBorder(5,10,10,1100));
		
		goikoPanela.setLayout(new BoxLayout(goikoPanela, BoxLayout.Y_AXIS));
		goikoPanela.add(hizkuntzaEtaIrten);
		goikoPanela.add(argazkiakJaitsiPanel);
		goikoPanela.add(argazkiakIgoPanel);
		goikoPanela.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
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
		ezkerrekoPanela.setBorder(BorderFactory.createEmptyBorder(10,10,490,10));
		pantailaNagusia.getContentPane().add(ezkerrekoPanela, BorderLayout.WEST);
	}
	
	public void ekialdePanela() {
		eskumakoPanela = new JPanel();
		bilatuText = new JTextField(LUZERA);
		bilatuBotoia = new JButton("Bilatu");
		eskumakoPanela.setLayout(new BoxLayout(eskumakoPanela, BoxLayout.X_AXIS));
		eskumakoPanela.add(bilatuText);
		eskumakoPanela.add(bilatuBotoia);
		eskumakoPanela.setBorder(BorderFactory.createEmptyBorder(10,10,490,10));
		pantailaNagusia.getContentPane().add(eskumakoPanela, BorderLayout.EAST);
	}
	
	public void hegoaldePanela() {
		behekoPanela = new JPanel();
		behekoPanela.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		pantailaNagusia.getContentPane().add(behekoPanela, BorderLayout.SOUTH);
	}
	
	public void panelaEraikitzen() {		
		pantailaNagusia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pantailaNagusia.pack();
		pantailaNagusia.setVisible(true);
	}

	public static void main(String[] args) {		 
		PantailaNagusia n = new PantailaNagusia();
		n.panelaEraikitzen();
	}
}