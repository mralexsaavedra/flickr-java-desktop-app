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
		
		argazkiakJaitsiBotoia = new JButton("Flickr-etik argazkiak jaitsi");
		argazkiakJaitsiPanel.setLayout(new BoxLayout(argazkiakJaitsiPanel, BoxLayout.X_AXIS));
		argazkiakJaitsiPanel.add(argazkiakJaitsiBotoia);
		
		argazkiakIgoBotoia = new JButton("Flickr-era argazkiak igo");
		argazkiakIgoPanel.setLayout(new BoxLayout(argazkiakIgoPanel, BoxLayout.X_AXIS));
		argazkiakIgoPanel.add(argazkiakIgoBotoia);
		
		goikoPanela.setLayout(new BoxLayout(goikoPanela, BoxLayout.Y_AXIS));
		goikoPanela.add(hizkuntzaEtaIrten);
		goikoPanela.add(argazkiakJaitsiPanel);
		goikoPanela.add(argazkiakIgoPanel);
		pantailaNagusia.getContentPane().add(goikoPanela, BorderLayout.NORTH);
	}
	
	public void mendebaldePanela() {
		
	}
	
	public void ekialdePanela() {
		
	}
	
	public void hegoaldePanela() {
		
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