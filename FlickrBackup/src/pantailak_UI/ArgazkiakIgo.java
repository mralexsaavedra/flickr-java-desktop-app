package pantailak_UI;

import java.io.*;
import java.util.Properties;


import java.awt.*;
import javax.swing.*;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.util.IOUtilities;

import flickrJava.Bildumak;

public class ArgazkiakIgo extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static private final String newline = "\n";
	JButton aukeratuButton, igoButton;
	JTextArea log;
	JFileChooser fc;

	static String apiKey;
	static String sharedSecret;
	Flickr f;
	REST rest;
	RequestContext requestContext;
	Properties properties = null;
	private String erabiltzaile;
	private JDesktopPane desktop;
	private String setupProperties;

	public ArgazkiakIgo(String email, JDesktopPane desktopPane, String path) throws IOException {
		super(new BorderLayout());
		this.desktop = desktopPane;
		this.erabiltzaile = email;
		this.setupProperties = path;
		FileInputStream in = null;
		try {
			in = new FileInputStream(setupProperties);
			properties = new Properties();
			properties.load(in);
		} finally {
			IOUtilities.close(in);
		}
		f = new Flickr(properties.getProperty("apiKey"), properties.getProperty("secret"), new REST());
		requestContext = RequestContext.getRequestContext();
		Auth auth = new Auth();
		auth.setPermission(Permission.READ);
		auth.setToken(properties.getProperty("token"));
		auth.setTokenSecret(properties.getProperty("tokensecret"));
		requestContext.setAuth(auth);
		Flickr.debugRequest = false;
		Flickr.debugStream = false;

		log = new JTextArea(5, 20);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);

		fc = new JFileChooser();

		aukeratuButton = new JButton("Aukeratu");
		aukeratuButton.addActionListener(actionListener -> this.aukeratu());

		igoButton = new JButton("Igo");
		igoButton.addActionListener(actionListener -> {
			try {
				this.igoArgazkia();
			} catch (IOException | FlickrException e) {
				e.printStackTrace();
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(aukeratuButton);
		buttonPanel.add(igoButton);

		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
	}

	private void igoArgazkia() throws IOException, FlickrException {
		File imageFile = fc.getSelectedFile();
		Bildumak bildumak = new Bildumak(erabiltzaile, setupProperties);
		bildumak.argazkiaIgoBildumaBatean(desktop, imageFile);
		log.append("Igota: " + imageFile.getName() + "." + newline);
	}

	private void aukeratu() {
		int returnVal = fc.showOpenDialog(ArgazkiakIgo.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			log.append("Aukeratutakoa: " + file.getName() + "." + newline);
		} else {
			log.append("Aukeratua kantzelatuta" + newline);
		}
	}

}
