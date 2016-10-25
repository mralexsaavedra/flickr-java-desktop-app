package flickr;

import java.awt.*;
import java.util.Properties;
import java.util.Vector;
import javax.swing.*;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photosets.Photoset;
import com.flickr4java.flickr.photosets.Photosets;
import com.flickr4java.flickr.photosets.PhotosetsInterface;
import com.flickr4java.flickr.util.IOUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

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
	private JPanel bildumenKontaketaPanela;
	private JPanel logoPanela;
	private JLabel picture;
	private JPanel behekoPanela;
	private final int LUZERA = 20;
	
	private JScrollPane bildumenScrollPanela;
	
	private Flickr f;
	Properties properties = null;
	
	public PantailaNagusia() throws IOException {
		super(new BorderLayout());
				
		InputStream in = null;
		try {
			in = getClass().getResourceAsStream("/setup.properties");
			properties = new Properties();
			properties.load(in);
		} finally {
			IOUtilities.close(in);
		}
		f = new Flickr(properties.getProperty("apiKey"), properties.getProperty("secret"), new REST());
		
		pantailaNagusia = new JFrame("FlickrBackup");
		goikoPanela = new JPanel();
		hizkuntzaEtaIrten = new JPanel();
		argazkiakJaitsiPanel = new JPanel();
		argazkiakIgoPanel = new JPanel();
		ezkerrekoPanela = new JPanel();		
		eskumakoPanela = new JPanel();
		bilatuText = new JTextField(LUZERA);
		bilatuBotoia = new JButton("Bilatu");
		behekoPanela = new JPanel();
		bildumenKontaketaPanela = new JPanel();
		bildumakLabel = new JLabel("Bildumak :   " );
		bildumakGuztira = new JLabel(bildumenKontaketa());
		picture = new JLabel(new ImageIcon(getClass().getResource("images/logo-flickr.png")));
		logoPanela = new JPanel();
		
		irtenBotoia = new JButton("Irten");
		irtenBotoia.addActionListener(this);
		argazkiakIgoBotoia = new JButton("Flickr-era argazkiak igo");
		argazkiakJaitsiBotoia = new JButton("Flickr-etik argazkiak jaitsi");		
		argazkiakJaitsiBotoia.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					try {
						ArgazkiakPantailaratu t = new ArgazkiakPantailaratu();
						System.out.println("Argazkiak pantailartzen...");
						t.showPhotos();						
						System.out.println("Argazkiak jatsi dira");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}
		});
		
		hizkuntzElementuak = new Vector<String>();
		hizkuntzElementuak.add("Euskara");
		hizkuntzElementuak.add("English");
		hizkuntzElementuak.add("Español");
		hizkuntzak = new JComboBox<String>(hizkuntzElementuak);
		
		bildumaElementuak = getBildumak();
		bildumak = new JComboBox<String>(bildumaElementuak);
		argazkiakBotoia = new JButton("Argazkiak");
		
		iparraldePanela();
		mendebaldePanela();
		ekialdePanela();
		hegoaldePanela();
		
		bildumenScrollPanela = new JScrollPane();
		bildumenScrollPanela.setPreferredSize(new Dimension(50, 400));
		pantailaNagusia.getContentPane().add(bildumenScrollPanela, BorderLayout.CENTER);
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
		bildumenKontaketaPanela.setLayout(new FlowLayout());
		bildumenKontaketaPanela.add(bildumakLabel);
		bildumenKontaketaPanela.add(bildumakGuztira);
		
		logoPanela.setLayout(new FlowLayout());
		logoPanela.add(picture);
		
		behekoPanela.setLayout(new GridLayout(1,5));
		behekoPanela.add(bildumenKontaketaPanela);
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
	
	public String bildumenKontaketa(){
		String userId = properties.getProperty("nsid");		
		PhotosetsInterface photosetsInterface = f.getPhotosetsInterface();
		Photosets photosets;
		
		int photosetsCount = 0;

		try {
			photosets = photosetsInterface.getList(userId);
			photosetsCount = photosets.getTotal();
			
		} catch (FlickrException e) {
			e.printStackTrace();
		}
		return String.valueOf(photosetsCount);
	}
	
	public Vector<String> getBildumak(){		
		Vector<String> bildumenIzenak = new Vector<String>();
		
		String userId = properties.getProperty("nsid");
		PhotosetsInterface photosetsInterface = f.getPhotosetsInterface();
		Photosets photosets;
		
		try {
			photosets = photosetsInterface.getList(userId);
			java.util.Collection<Photoset> bildumak = photosets.getPhotosets();
			
			for (Photoset photoset : bildumak) {
				String title = photoset.getTitle();
				bildumenIzenak.add(title);
			}
			
		} catch (FlickrException e) {
				e.printStackTrace();
		}
		return bildumenIzenak;
	}
	
	public void panelaEraikitzen() {		
		pantailaNagusia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pantailaNagusia.pack();
		pantailaNagusia.setVisible(true);
	}

	public static void main(String[] args) {		 
		PantailaNagusia n;
		try {
			n = new PantailaNagusia();
			n.panelaEraikitzen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}