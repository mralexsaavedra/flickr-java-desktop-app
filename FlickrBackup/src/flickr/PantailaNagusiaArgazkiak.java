package flickr;

import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PantailaNagusiaArgazkiak extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JFrame pantailaNagusia;
	
	private Vector<String> hizkuntzElementuak;
	private JComboBox<String> hizkuntzak;
	private JButton irtenBotoia;
	private JPanel hizkuntzaEtaIrten;
	private JButton hasieraraBotoia;
	private JPanel hasieraraPanel;
	private JPanel goikoPanela;
	
	private Vector<String> bildumaElementuak;
	private JComboBox<String> bildumak;
	private JButton argazkiakBotoia;
	private JPanel ezkerrekoPanela;
	
	private JButton igoBotoia;
	private JPanel erdikoPanela;
	
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
	
	public PantailaNagusiaArgazkiak() {
		super(new BorderLayout());
		
		hizkuntzElementuak = new Vector<String>();
		hizkuntzElementuak.add("Euskara");
		hizkuntzElementuak.add("English");
		hizkuntzElementuak.add("Español");
		hizkuntzak = new JComboBox<String>(hizkuntzElementuak);
		
		pantailaNagusia = new JFrame("FlickrBackup");
		goikoPanela = new JPanel();
		hizkuntzaEtaIrten = new JPanel();
		hasieraraPanel = new JPanel();
		irtenBotoia = new JButton("Irten");
		hasieraraBotoia = new JButton("Hasierara bueltatu");
		irtenBotoia.addActionListener(this);
		hasieraraBotoia.addActionListener(this);
		ezkerrekoPanela = new JPanel();		
		argazkiakBotoia = new JButton("Argazkiak");
		erdikoPanela = new JPanel();
		igoBotoia = new JButton("Argazkiak igo");
		eskumakoPanela = new JPanel();
		bilatuText = new JTextField(LUZERA);
		bilatuBotoia = new JButton("Bilatu");
		behekoPanela = new JPanel();
		bildumakLabel = new JLabel("Bildumak :   " );
		bildumakGuztira = new JLabel("bildumenKontaketa()");
		picture = new JLabel(new ImageIcon(getClass().getResource("images/logo-flickr.png")));
		bildumenPanela = new JPanel();
		logoPanela = new JPanel();
		
		bildumaElementuak = new Vector<String>();
		bildumaElementuak.add("Lehenengo bilduma");
		bildumaElementuak.add("Bigarren bilduma");
		bildumak = new JComboBox<String>(bildumaElementuak);
		
		iparraldePanela();
		mendebaldePanela();
		zentroPanela();
		ekialdePanela();
		hegoaldePanela();
	}
	
	public void iparraldePanela() {
		hizkuntzaEtaIrten.setLayout(new BoxLayout(hizkuntzaEtaIrten, BoxLayout.X_AXIS));
		hizkuntzaEtaIrten.add(hizkuntzak);
		hizkuntzaEtaIrten.add(irtenBotoia);
		hizkuntzaEtaIrten.setBorder(BorderFactory.createEmptyBorder(10,1000,10,10));
		
		hasieraraPanel.setLayout(new BoxLayout(hasieraraPanel, BoxLayout.X_AXIS));
		hasieraraPanel.add(hasieraraBotoia);
		hasieraraPanel.setBorder(BorderFactory.createEmptyBorder(10,10,5,1100));
		
		goikoPanela.setLayout(new BoxLayout(goikoPanela, BoxLayout.Y_AXIS));
		goikoPanela.add(hizkuntzaEtaIrten);
		goikoPanela.add(hasieraraPanel);
		goikoPanela.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
				
		pantailaNagusia.getContentPane().add(goikoPanela, BorderLayout.NORTH);
	}
	
	public void mendebaldePanela() {
		ezkerrekoPanela.setLayout(new FlowLayout());
		ezkerrekoPanela.add(bildumak);
		ezkerrekoPanela.add(argazkiakBotoia);
		ezkerrekoPanela.setBorder(BorderFactory.createEmptyBorder(10,10,450,10));
		pantailaNagusia.getContentPane().add(ezkerrekoPanela, BorderLayout.WEST);
	}
	
	public void zentroPanela() {
		erdikoPanela.setLayout(new BoxLayout(erdikoPanela, BoxLayout.X_AXIS));
		erdikoPanela.add(igoBotoia);
		erdikoPanela.setBorder(BorderFactory.createEmptyBorder(10,10,450,10));
		pantailaNagusia.getContentPane().add(erdikoPanela, BorderLayout.CENTER);
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
		if(e.getSource()==irtenBotoia) {
			logout();
		}
		else if(e.getSource()==hasieraraBotoia) {
			hasieraraBueltatu();
		}
	}
	
	public void logout() {
		pantailaNagusia.dispose();
		SesioaItxiPantaila itxi = new SesioaItxiPantaila();
		itxi.panelaEraikitzen();
	}
	
	public void hasieraraBueltatu() {
		pantailaNagusia.dispose();
		PantailaNagusia bueltatu;
		try {
			bueltatu = new PantailaNagusia();
			bueltatu.panelaEraikitzen();
		} catch (IOException e) {
			e.printStackTrace();
		}
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