package flickrJava;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.photosets.Photoset;
import com.flickr4java.flickr.photosets.Photosets;
import com.flickr4java.flickr.photosets.PhotosetsInterface;
import com.flickr4java.flickr.util.IOUtilities;

import kudeatzaileak.Kudeatzailea;
import pantailak_UI.MyInternalFrame;

public class Bildumak {

	static String apiKey;
	static String sharedSecret;
	Flickr f;
	REST rest;
	RequestContext requestContext;
	Properties properties = null;
	private String erabiltzaile;
	private String setupProperties;

	public Bildumak(String email, String path) throws IOException {
		this.setupProperties = path;
		this.erabiltzaile = email;
		InputStream in = null;
		try {
			in = new ByteArrayInputStream(setupProperties.getBytes());
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
	}

	public  List<String[]> showPhotosets() {
		   PhotosetsInterface photosetsInterface  = f.getPhotosetsInterface();
			
			String userId = properties.getProperty("nsid");
			
			List<String[]> emaitza = new ArrayList<String[]>();
			
			try {
				Photosets photosets = photosetsInterface.getList(userId);
				Collection<Photoset> bildumak = photosets.getPhotosets();
				
				for (Photoset  bilduma : bildumak) {
					String[] res = new String[4];
					res[0] = bilduma.getId();
					res[1] = bilduma.getTitle();
					res[2] = bilduma.getDescription();
					res[3] = String.valueOf(bilduma.getPhotoCount());
					emaitza.add(res);
				}
			} catch (FlickrException e) {
				e.printStackTrace();
			}	
			return emaitza;
	}
	
	public Photoset bildumaLortu(String bildumaIzena){
		Photoset bilduma = null;
		
		PhotosetsInterface photosetsInterface  = f.getPhotosetsInterface();
		
		String userId = properties.getProperty("nsid");
				
		try {
			Photosets photosets = photosetsInterface.getList(userId);
			Collection<Photoset> bildumak = photosets.getPhotosets();
			for (Photoset photoset : bildumak){
				if (bildumaIzena.equals(photoset.getTitle())){
					bilduma = photoset;
				}		
			}
			
		} catch (FlickrException e) {
			e.printStackTrace();
		}
		return bilduma;
	}
	
	public void bildumakGordeDB(){
		List<String[]> bildumak = this.showPhotosets();
		for (String[] bilduma : bildumak){
			Kudeatzailea.getInstantzia().bildumakGorde(bilduma[0], bilduma[1], bilduma[2],Integer.parseInt(bilduma[3]), erabiltzaile);
		}
	}
	
	public void argazkiaIgoBildumaBatean(JDesktopPane desktop, File imageFile){
		MyInternalFrame internalFrame = new MyInternalFrame();
		desktop.add(internalFrame);
		JPanel panela = new JPanel();
		panela.setLayout(new BoxLayout(panela, BoxLayout.X_AXIS));
		Vector<String> elementuak = new Vector<String>();
		List<String[]> emaitzak = Kudeatzailea.getInstantzia().getBildumak(erabiltzaile);
		for (String[] bilduma : emaitzak) {
			elementuak.add(bilduma[1]);
		}
		JComboBox<String> elementuenBox = new JComboBox<String>(elementuak);	
		panela.add(elementuenBox);
		JButton okBotoia = new JButton("OK");
		panela.add(okBotoia);
		okBotoia.addActionListener(actionListener -> {
			try {
				internalFrame.dispose();
				Argazkiak argazkiak = new Argazkiak(erabiltzaile, setupProperties);
				String bildumaID = Kudeatzailea.getInstantzia().getBilduma(elementuenBox.getSelectedItem().toString());
				if (argazkiaBadago(bildumaID, imageFile))
					JOptionPane.showMessageDialog(null, "Argazki hori badago bilduma horretan", "WARNING", JOptionPane.WARNING_MESSAGE);
				else
					argazkiak.igoArgazkia(imageFile, bildumaID);
			} catch (IOException | FlickrException e1) {
				e1.printStackTrace();
			}
		});
		panela.setOpaque(true);
		internalFrame.setContentPane(panela);
		internalFrame.pack();
		try {
			internalFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
		}
		internalFrame.setVisible(true);
	}
		
	public boolean argazkiaBadago(String bildumaID, File irudia){
		 boolean badago = false;
		 List<String[]> emaitzak = Kudeatzailea.getInstantzia().getBildumaBatenIDGuztiak(bildumaID);
		 String argazkiID = Kudeatzailea.getInstantzia().getArgazki(irudia.getName());
		 	if (emaitzak.contains(argazkiID))
		 		badago = true;
		 return badago;
	}
	
	public  PhotosetsInterface bildumaGuztiakLortu() {
          return f.getPhotosetsInterface();
	}
	
}