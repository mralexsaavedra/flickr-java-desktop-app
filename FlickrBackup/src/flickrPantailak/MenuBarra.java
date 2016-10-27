package flickrPantailak;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import flickrJava.ArgazkiakPantailaratu;

public class MenuBarra extends JMenuBar implements ActionListener {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private PantailaNagusia nagusia;

	public MenuBarra() {
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
		} else if ("argazkiak igo".equals(e.getActionCommand())) {
			System.out.println("ArgazkiakIgo klasea");
		} else if ("logout".equals(e.getActionCommand())) {
			SesioaItxiPantaila itxi = new SesioaItxiPantaila();
			itxi.panelaEraikitzen();
		} else if ("bildumak pantailaratu".equals(e.getActionCommand())) {
			/*
			 * BildumakPantailaratu bp; 
			 * try { bp = new BildumakPantailaratu();
			 * bp.showPhotosets(); } catch (IOException e1) {
			 * e1.printStackTrace();
			 */
			createFrame();
		} else if("argazkiak pantailaratu".equals(e.getActionCommand())) {
			ArgazkiakPantailaratu ap;
			try {
				ap = new ArgazkiakPantailaratu();
				ap.showPhotos();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			System.exit(0);
		}
	}


	protected void createFrame() {
		MyInternalFrame frame = new MyInternalFrame();
		nagusia.add(frame);
		try {
			frame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
		}
	}

}