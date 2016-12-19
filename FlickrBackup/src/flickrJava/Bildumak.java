package flickrJava;

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

	public Bildumak(String email) throws IOException {
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
	}

	public  List<String[]> showPhotosets() {
		   PhotosetsInterface photosetsInterface  = f.getPhotosetsInterface();
			
			String userId = properties.getProperty("nsid");
			
			List<String[]> emaitza = new ArrayList<String[]>();
			
			try {
				Photosets photosets = photosetsInterface.getList(userId);
				Collection<Photoset> bildumak = photosets.getPhotosets();
				
				for (Photoset  bilduma : bildumak) {
					String[] res = new String[3];
					res[0] = bilduma.getTitle();
					res[1] = bilduma.getDescription();
					res[2] = String.valueOf(bilduma.getPhotoCount());
					emaitza.add(res);
				}
			} catch (FlickrException e) {
				e.printStackTrace();
			}	
			return emaitza;
	}
	
	public Photoset bildumaLortu(String bildumaTitle){
		Photoset bilduma = null;
		
		PhotosetsInterface photosetsInterface  = f.getPhotosetsInterface();
		
		String userId = properties.getProperty("nsid");
				
		try {
			Photosets photosets = photosetsInterface.getList(userId);
			Collection<Photoset> bildumak = photosets.getPhotosets();
			for (Photoset photoset : bildumak){
				if (bildumaTitle.equals(photoset.getTitle())){
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
			Kudeatzailea.getInstantzia().bildumakGorde(bilduma[0], bilduma[1], Integer.parseInt(bilduma[2]), erabiltzaile);
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
			elementuak.add(bilduma[0]);
		}
		JComboBox<String> elementuenBox = new JComboBox<String>(elementuak);	
		panela.add(elementuenBox);
		JButton okBotoia = new JButton("OK");
		panela.add(okBotoia);
		okBotoia.addActionListener(actionListener -> {
			try {
				internalFrame.dispose();
				if (argazkiaBadago(elementuenBox.getSelectedItem().toString(), imageFile)==true)
					JOptionPane.showMessageDialog(null, "Argazki hori badago bilduma horretan", "WARNING", JOptionPane.WARNING_MESSAGE);
				else
					igoArgazkia(imageFile, elementuenBox.getSelectedItem().toString());
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
	
	public void igoArgazkia(File imageFile, String bilduma) throws IOException, FlickrException{
		Argazkiak argazkiak= new Argazkiak(erabiltzaile);
		String argazkia = argazkiak.argazkiakIgo(imageFile);
        PhotosetsInterface iface = f.getPhotosetsInterface();
        iface.addPhoto(bildumaLortu(bilduma).getId(), argazkia);		
	}
	
	public boolean argazkiaBadago(String bilduma, File irudia){
		 boolean badago = false;
		 MD5 md5 = new MD5();
		 List<String[]> emaitzak = Kudeatzailea.getInstantzia().getBildumaBatenMD5Guztiak(bilduma);
		 for (String[] argazkia : emaitzak){
			 try {
				if (md5.MD5CheckSum(irudia).equals(argazkia[0]))
					 badago = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		 return badago;
	}
	
}