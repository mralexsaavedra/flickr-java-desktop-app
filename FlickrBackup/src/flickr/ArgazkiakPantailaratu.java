package flickr;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoSet;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photosets.Photosets;
import com.flickr4java.flickr.util.IOUtilities;

public class ArgazkiakPantailaratu {

	static String apiKey;

	static String sharedSecret;

	Flickr f;

	REST rest;

	RequestContext requestContext;

	Properties properties = null;

	public ArgazkiakPantailaratu() throws IOException {
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
        	ArgazkiakPantailaratu t = new ArgazkiakPantailaratu();
            t.showPhotos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

	public void showPhotos() {
		   PhotosInterface photosInterface  = f.getPhotosInterface();
			
			String userId = properties.getProperty("nsid");
			
			try {
				//Photo photos = photosInterface.getPhoto(userId);
				Collection<Photo> argazkiak = (Collection<Photo>) photosInterface.getPhoto(userId);
				
				for (Photo  argazkia : argazkiak) {
					System.out.print(argazkia.getTitle() +  ":");
					System.out.println(argazkia.getDescription() + ":");
					System.out.println(argazkia.getDateAdded() + ":");
					System.out.println(argazkia.getDatePosted() + ":");
					System.out.println(argazkia.getDateTaken() + ":");
					System.out.println(argazkia.getGeoData() + ":");
					System.out.println(argazkia.getTags());
				}
			} catch (FlickrException e) {
				e.printStackTrace();
			}	
			
	}
	
}