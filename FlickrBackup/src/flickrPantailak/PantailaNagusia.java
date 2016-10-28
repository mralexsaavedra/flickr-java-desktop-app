package flickrPantailak;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import flickrJava.Argazkiak;

public class PantailaNagusia extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PantailaNagusia(){
		this.setTitle("FlickrBackup");
		this.setJMenuBar(createMenuBar());
				
		//this.setSize(500, 400);
		int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new PantailaNagusia();
	}
	
	protected JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		// 1. zutabea
		JMenu menu = new JMenu("Flickr");
		menu.setMnemonic(KeyEvent.VK_D);
		menuBar.add(menu);

		// 1. zutabearen 1. aukera
		JMenuItem logoutMenuItem = new JMenuItem("Logout");
		logoutMenuItem.setActionCommand("logout");
		logoutMenuItem.addActionListener(this);
		menu.add(logoutMenuItem);

		// 1. zutabearen 2 aukera
		JMenuItem menuItem = new JMenuItem("Quit");
		menuItem.setMnemonic(KeyEvent.VK_Q);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
		menuItem.setActionCommand("quit");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		// 2. zutabea
		JMenu flickrMenua = new JMenu("Akzioak");
		menuBar.add(flickrMenua);

		// 2. zutabeare 1 aukera
		JMenuItem argazkiakJaitsiMenuItem = new JMenuItem("Argazkiak jaitsi");
		argazkiakJaitsiMenuItem.setActionCommand("argazkiak jaitsi");
		argazkiakJaitsiMenuItem.addActionListener(this);
		flickrMenua.add(argazkiakJaitsiMenuItem);

		// 2. zutabearen 2. aukera
		JMenuItem argazkiakIgoMenuItem = new JMenuItem("Argazkiak igo");
		argazkiakIgoMenuItem.setActionCommand("argazkiak igo");
		argazkiakIgoMenuItem.addActionListener(this);
		flickrMenua.add(argazkiakIgoMenuItem);

		// 3. zutabea
		JMenu ikusiMenua = new JMenu("Ikusi");
		menuBar.add(ikusiMenua);

		// 3. zutabearen 1. aukera
		JMenuItem bildumakMenuItem = new JMenuItem("Bildumak Pantailaratu");
		bildumakMenuItem.setActionCommand("bildumak pantailaratu");
		bildumakMenuItem.addActionListener(this);
		ikusiMenua.add(bildumakMenuItem);

		// 3. zutabearen 2. aukera
		JMenuItem argazkiakMenuItem = new JMenuItem("Argazkiak Pantailaratu");
		argazkiakMenuItem.setActionCommand("argazkiak pantailaratu");
		argazkiakMenuItem.addActionListener(this);
		ikusiMenua.add(argazkiakMenuItem);

		// 4. zutabea
		JMenu hizkuntzaMenua = new JMenu("Hizkuntza");
		menuBar.add(hizkuntzaMenua);

		// 4. zutabearen 1. aukera
		JMenuItem euskeraMenuItem = new JMenuItem("Euskera");
		hizkuntzaMenua.add(euskeraMenuItem);

		// 4. zutabearen 2. aukera
		JMenuItem ingelesaMenuItem = new JMenuItem("English");
		hizkuntzaMenua.add(ingelesaMenuItem);

		// 4. zutabearen 3. aukera
		JMenuItem gaztelaniaMenuItem = new JMenuItem("Español");
		hizkuntzaMenua.add(gaztelaniaMenuItem);

		return menuBar;
	}

	public void actionPerformed(ActionEvent e) {
		if ("argazkiak jaitsi".equals(e.getActionCommand())) {
			System.out.println("ArgazkiakPantailaratu klasea");
		} 
		else if ("argazkiak igo".equals(e.getActionCommand())) {
			System.out.println("ArgazkiakIgo klasea");
		} 
		else if ("logout".equals(e.getActionCommand())) {
			new SesioaItxiPantaila().panelaEraikitzen();
		} 
		else if ("bildumak pantailaratu".equals(e.getActionCommand())) {
			getContentPane().add(new BildumakUI(), BorderLayout.CENTER);
		} 
		else if("argazkiak pantailaratu".equals(e.getActionCommand())) {
			Argazkiak ap;
			try {
				ap = new Argazkiak();
				ap.showPhotos();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} 
		else {
			System.exit(0);
		}
	}


}
