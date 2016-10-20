package flickr;

import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantailaNagusia extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JFrame pantailaNagusia;
	
	private Vector<String> hizkuntzElementuak;
	private JComboBox<String> hizkuntzak;
	private JButton irtenBotoia;
	private JPanel hizkuntzaEtaIrten;
	private JButton argazkiakJaitsiBotoia;
	private JPanel argazkiakJaitsiPanel;
	private JButton argazkiakIgoBotoia;
	private JPanel argazkiakIgoPanel;
	private JPanel goikoPanela;
	
	private Vector<String> bildumaElementuak;
	private JComboBox<String> bildumak;
	private JButton argazkiakBotoia;
	private JPanel ezkerrekoPanela;
	
	private JTextField bilatuText;
	private JButton bilatuBotoia;
	private JPanel eskumakoPanela;
	
	private JLabel bildumakLabel;
	private JLabel bildumakGuztira;
	private JPanel bildumenPanela;
	private JPanel logoPanela;
	private JLabel picture;
	private JPanel behekoPanela;
	private final int LUZERA = 20;
	
	public PantailaNagusia() {
		super(new BorderLayout());
		
		pantailaNagusia = new JFrame("FlickrBackup");
		goikoPanela = new JPanel();
		hizkuntzaEtaIrten = new JPanel();
		argazkiakJaitsiPanel = new JPanel();
		argazkiakIgoPanel = new JPanel();
		irtenBotoia = new JButton("Irten");
		argazkiakJaitsiBotoia = new JButton("Flickr-etik argazkiak jaitsi");
		argazkiakIgoBotoia = new JButton("Flickr-era argazkiak igo");
		irtenBotoia.addActionListener(this);
		ezkerrekoPanela = new JPanel();		
		eskumakoPanela = new JPanel();
		bilatuText = new JTextField(LUZERA);
		bilatuBotoia = new JButton("Bilatu");
		behekoPanela = new JPanel();
		bildumakLabel = new JLabel("Bildumak :   " );
		bildumakGuztira = new JLabel("bildumenKontaketa()");
		picture = new JLabel(new ImageIcon(getClass().getResource("images/logo-flickr.png")));
		logoPanela = new JPanel();

		bildumenPanela = new JPanel();
		hizkuntzElementuak = new Vector<String>();
		hizkuntzElementuak.add("Euskara");
		hizkuntzElementuak.add("English");
		hizkuntzElementuak.add("Español");
		hizkuntzak = new JComboBox<String>(hizkuntzElementuak);
		
		bildumaElementuak = new Vector<String>();
		bildumaElementuak.add("Lehenengo bilduma");
		bildumaElementuak.add("Bigarren bilduma");
		bildumak = new JComboBox<String>(bildumaElementuak);
		argazkiakBotoia = new JButton("Argazkiak");
		
		iparraldePanela();
		mendebaldePanela();
		ekialdePanela();
		hegoaldePanela();
	}
	
	public void iparraldePanela() {	
		hizkuntzaEtaIrten.setLayout(new BoxLayout(hizkuntzaEtaIrten, BoxLayout.X_AXIS));
		hizkuntzaEtaIrten.add(hizkuntzak);
		hizkuntzaEtaIrten.add(irtenBotoia);
		hizkuntzaEtaIrten.setBorder(BorderFactory.createEmptyBorder(10,1000,10,10));
		
		argazkiakJaitsiPanel.setLayout(new BoxLayout(argazkiakJaitsiPanel, BoxLayout.X_AXIS));
		argazkiakJaitsiPanel.add(argazkiakJaitsiBotoia);
		argazkiakJaitsiPanel.setBorder(BorderFactory.createEmptyBorder(10,10,5,1100));
		
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
		ezkerrekoPanela.setLayout(new BoxLayout(ezkerrekoPanela, BoxLayout.X_AXIS));
		ezkerrekoPanela.add(bildumak);
		ezkerrekoPanela.add(argazkiakBotoia);
		ezkerrekoPanela.setBorder(BorderFactory.createEmptyBorder(10,10,450,10));
		pantailaNagusia.getContentPane().add(ezkerrekoPanela, BorderLayout.WEST);
	}
	
	public void ekialdePanela() {
		eskumakoPanela.setLayout(new BoxLayout(eskumakoPanela, BoxLayout.X_AXIS));
		eskumakoPanela.add(bilatuText);
		eskumakoPanela.add(bilatuBotoia);
		eskumakoPanela.setBorder(BorderFactory.createEmptyBorder(10,10,450,10));
		pantailaNagusia.getContentPane().add(eskumakoPanela, BorderLayout.EAST);
	}
	
	public void hegoaldePanela() {
		bildumenPanela.setLayout(new FlowLayout());
		bildumenPanela.add(bildumakLabel);
		bildumenPanela.add(bildumakGuztira);
		
		logoPanela.setLayout(new FlowLayout());
		logoPanela.add(picture);
		
		behekoPanela.setLayout(new GridLayout(1,5));
		behekoPanela.add(bildumenPanela);
		behekoPanela.add(new JPanel());
		behekoPanela.add(logoPanela);
		behekoPanela.add(new JPanel());
		behekoPanela.add(new JPanel());
		
		pantailaNagusia.getContentPane().add(behekoPanela, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e) {
		logout();
	}
	
	public void logout() {
		pantailaNagusia.dispose();
		SesioaItxiPantaila itxita = new SesioaItxiPantaila();
		itxita.panelaEraikitzen();
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