package flickrJava;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

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

public class Bildumak {

	static String apiKey;

	static String sharedSecret;

	Flickr f;

	REST rest;

	RequestContext requestContext;

	Properties properties = null;

	public Bildumak() throws IOException {
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
	
    public static void main(String[] args) {
        try {
        	Bildumak t = new Bildumak();
            t.showPhotosets();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

	public List<String[]> showPhotosets() {
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
					//System.out.print(bilduma.getTitle() +  ":");
					//System.out.println(bilduma.getDescription());
				}
			} catch (FlickrException e) {
				e.printStackTrace();
			}	
			return emaitza;
	}
	
	public int blidumenKontaketa() {
		String userId = properties.getProperty("nsid");
		PhotosetsInterface photosetsInterface = f.getPhotosetsInterface();
		Photosets photosets = null;
		try {
			photosets = photosetsInterface.getList(userId);
		} catch (FlickrException e) {
			e.printStackTrace();
		}
		return photosets.getTotal();
	}
	
}