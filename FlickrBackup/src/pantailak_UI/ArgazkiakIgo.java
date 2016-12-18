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
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.flickr4java.flickr.uploader.Uploader;
import com.flickr4java.flickr.util.IOUtilities;

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

	public ArgazkiakIgo(String email) throws IOException {
		super(new BorderLayout());

		this.erabiltzaile = email;
		InputStream in = null;
		try {
			in = getClass().getResourceAsStream("/setup.properties");
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

	public void igoArgazkia() throws IOException, FlickrException {
		File imageFile = fc.getSelectedFile();
		InputStream in = null;
		Uploader uploader = f.getUploader();
		PhotosInterface pint = f.getPhotosInterface();
		try {
			in = new FileInputStream(imageFile);
			UploadMetaData metaData = buildPrivatePhotoMetadata();
			metaData.setPublicFlag(true);
			metaData.setTitle(imageFile.getName());
			String photoId = uploader.upload(in, metaData);
		} finally {
			IOUtilities.close(in);
		}
		log.append("Igota: " + imageFile.getName() + "." + newline);
	}

	public void aukeratu() {
		int returnVal = fc.showOpenDialog(ArgazkiakIgo.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			// This is where a real application would open the file.
			log.append("Aukeratutakoa: " + file.getName() + "." + newline);
		} else {
			log.append("Aukeratua kantzelatuta" + newline);
		}
	}

	private UploadMetaData buildPrivatePhotoMetadata() {
		UploadMetaData uploadMetaData = new UploadMetaData();
		uploadMetaData.setPublicFlag(false);
		return uploadMetaData;
	}

	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = ArgazkiakIgo.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Ezin da ireki: " + path);
			return null;
		}
	}

	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("Flickr");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new ArgazkiakIgo("alexander"));
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		UIManager.put("swing.boldMetal", Boolean.FALSE);
	}

}
