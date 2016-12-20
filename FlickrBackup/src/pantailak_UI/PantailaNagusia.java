package pantailak_UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.flickr4java.flickr.FlickrException;

import flickrJava.Argazkiak;
import flickrJava.Bildumak;
import sesioPantailak_UI.SesioaItxiPantaila;
import taulak_UI.ArgazkienTaula;
import taulak_UI.BildumenTaula;
import zuhaitza_UI.Zuhaitza;

public class PantailaNagusia extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String erabiltzaile;
	private JDesktopPane desktop;
	private String setupProperties;
	JFileChooser fc = new JFileChooser();

	public PantailaNagusia(String email) {
		this.erabiltzaile = email;
		this.setTitle("FlickrBackup");
		this.setJMenuBar(createMenuBar());
		desktop = new JDesktopPane();
		this.setContentPane(desktop);
		desktop.setDragMode(JDesktopPane.LIVE_DRAG_MODE);
		desktop.setBackground(Color.LIGHT_GRAY);
		this.setupPropertiesLortu();
	}

	public void eraikiFrame() {
		int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	protected JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		// 1. zutabea
		JMenu menu = new JMenu("Flickr");
		menu.setMnemonic(KeyEvent.VK_D);
		menuBar.add(menu);

		// 1. zutabearen 1. aukera
		JMenuItem pantailaNagusiaMenuItem = new JMenuItem("Zuhaitza");
		pantailaNagusiaMenuItem.addActionListener(actionListener -> this.zuhaitzaEraiki());
		menu.add(pantailaNagusiaMenuItem);

		// 1. zutabearen 2. aukera
		JMenuItem sinkronizatuMenuItem = new JMenuItem("Sinkronizatu");
		sinkronizatuMenuItem.addActionListener(actionListener -> {
			try {
				this.sinkronizatu();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		menu.add(sinkronizatuMenuItem);
		
		// 1. zutabearen 3. aukera
		JMenuItem argazkiakIgoMenuItem = new JMenuItem("Argazkiak igo");
		argazkiakIgoMenuItem.addActionListener(actionListener -> {
			try {
				this.argazkiakIgo();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		menu.add(argazkiakIgoMenuItem);

		// 1. zutabearen 4. aukera
		JMenuItem logoutMenuItem = new JMenuItem("Logout");
		logoutMenuItem.addActionListener(actionListener -> this.logout());
		menu.add(logoutMenuItem);

		// 1. zutabearen 5. aukera
		JMenuItem menuItem = new JMenuItem("Irten");
		menuItem.setMnemonic(KeyEvent.VK_Q);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
		menuItem.addActionListener(actionListener -> System.exit(0));
		menu.add(menuItem);

		// 2. zutabea
		JMenu ikusiMenua = new JMenu("Ikusi");
		menuBar.add(ikusiMenua);

		// 2. zutabearen 1. aukera
		JMenuItem bildumakMenuItem = new JMenuItem("Bildumak Pantailaratu");
		bildumakMenuItem.addActionListener(actionListener -> this.bildumakPantailaratu());
		ikusiMenua.add(bildumakMenuItem);

		// 2. zutabearen 2. aukera
		JMenuItem argazkiakMenuItem = new JMenuItem("Argazkiak Pantailaratu");
		argazkiakMenuItem.addActionListener(actionListener -> this.argazkiakPantailaratu());
		ikusiMenua.add(argazkiakMenuItem);

		// 3. zutabea
		JMenu hizkuntzaMenua = new JMenu("Hizkuntza");
		menuBar.add(hizkuntzaMenua);

		// 3. zutabearen 1. aukera
		JMenuItem euskeraMenuItem = new JMenuItem("Euskera");
		hizkuntzaMenua.add(euskeraMenuItem);

		// 3. zutabearen 2. aukera
		JMenuItem ingelesaMenuItem = new JMenuItem("English");
		hizkuntzaMenua.add(ingelesaMenuItem);

		// 4. zutabearen 3. aukera
		JMenuItem gaztelaniaMenuItem = new JMenuItem("Español");
		hizkuntzaMenua.add(gaztelaniaMenuItem);

		return menuBar;
	}

	private void logout() {
		this.dispose();
		new SesioaItxiPantaila(erabiltzaile).panelaEraikitzen();
	}

	private void argazkiakPantailaratu() {
		MyInternalFrame internalFrame = new MyInternalFrame();
		desktop.add(internalFrame);
		ArgazkienTaula taula = new ArgazkienTaula(erabiltzaile);
		taula.setOpaque(true);
		JPanel  botoienPanela = new JPanel();
		JButton eguneratuBotoia = new JButton("Eguneratu");
		JButton ezabatuBotoia = new JButton("Ezabatu");
		internalFrame.add(taula, BorderLayout.CENTER);
		botoienPanela.add(eguneratuBotoia);
		//eguneratuBotoia.addActionListener(actionListener -> argazkiakEguneratu());
		botoienPanela.add(ezabatuBotoia);
		//ezabatuBotoia.addActionListener(actionListener -> argazkiakEzabatu());
		internalFrame.add(botoienPanela, BorderLayout.SOUTH);		
		internalFrame.pack();
		try {
			internalFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
		}
		internalFrame.setVisible(true);
	}

	private void bildumakPantailaratu() {
		MyInternalFrame internalFrame = new MyInternalFrame();
		desktop.add(internalFrame);
		BildumenTaula taula = new BildumenTaula(erabiltzaile);
		taula.setOpaque(true);
		JPanel  botoienPanela = new JPanel();
		JButton eguneratuBotoia = new JButton("Eguneratu");
		JButton ezabatuBotoia = new JButton("Ezabatu");
		internalFrame.add(taula, BorderLayout.CENTER);
		botoienPanela.add(eguneratuBotoia);
		eguneratuBotoia.addActionListener(actionListener -> {
			try {
				taula.eguneratu();
			} catch (FlickrException e1) {
				e1.printStackTrace();
			}
		});
		botoienPanela.add(ezabatuBotoia);
		ezabatuBotoia.addActionListener(actionListener -> taula.ezabatu());
		internalFrame.add(botoienPanela, BorderLayout.SOUTH);
		internalFrame.pack();
		try {
			internalFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
		}
		internalFrame.setVisible(true);
	}

	private void argazkiakIgo() throws IOException {
		MyInternalFrame internalFrame = new MyInternalFrame();
		desktop.add(internalFrame);
		internalFrame.setSize(300, 300);
		internalFrame.add(new ArgazkiakIgo(erabiltzaile, desktop, setupProperties));
		try {
			internalFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
		}
		internalFrame.setVisible(true);
	}

	private void zuhaitzaEraiki() {
		MyInternalFrame internalFrame = new MyInternalFrame();
		desktop.add(internalFrame);
		internalFrame.setSize(1000, 500);
		internalFrame.add(new Zuhaitza(erabiltzaile, setupProperties));
		try {
			internalFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
		}
		internalFrame.setVisible(true);
	}

	private void sinkronizatu() throws Exception {
		Bildumak bildumak;
		Argazkiak argazkiak;
		bildumak = new Bildumak(erabiltzaile, setupProperties);
		bildumak.bildumakGordeDB();
		argazkiak = new Argazkiak(erabiltzaile, setupProperties);
		argazkiak.argazkiakGorde();
		argazkiak.erlazioakGordeDB();
		JOptionPane.showMessageDialog(null, "Dena eguneratu egin da", "ABISUA", JOptionPane.DEFAULT_OPTION,
				new ImageIcon(getClass().getResource("/icons/accept-tick-icon-12.png")));
	}
	
	public void setupPropertiesLortu(){
		int resp = JOptionPane.showConfirmDialog(null, "Esan no dagoen zure setup.properties", "Aplikazioa hasi baino lehen", JOptionPane.YES_NO_OPTION);
		if (resp==0){
			fc.showOpenDialog(this);
			File fitxategia = fc.getSelectedFile();
			setupProperties = fitxategia.getAbsolutePath();
		}
		else{
			this.dispose();
		}
	}

}
