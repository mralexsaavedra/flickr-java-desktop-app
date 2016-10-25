package flickr;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photosets.Photoset;
import com.flickr4java.flickr.photosets.Photosets;
import com.flickr4java.flickr.photosets.PhotosetsInterface;
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

		String userId = properties.getProperty("nsid");
		// String secret = properties.getProperty("secret");

		PhotosetsInterface photosetsInterface = f.getPhotosetsInterface();
		Photosets photosets;
		try {
			photosets = photosetsInterface.getList(userId);

			Collection<Photoset> bildumak = photosets.getPhotosets();

			for (Photoset photoset : bildumak) {
				String id = photoset.getId();
				String title = photoset.getTitle();
				String secret = photoset.getSecret();
				int photoCount = photoset.getPhotoCount();

				System.out.println("Title:" + title + " Secret:" + secret + " Count:" + photoCount);

				PhotoList<Photo> col;
				int PHOTOSPERPAGE = 2;
				int HOWMANYPAGES;
				if (photoCount>10)
					HOWMANYPAGES = (int) Math.ceil(photoCount / 10);
				else
					HOWMANYPAGES = 1;
				
				for (int page = 0; page <= HOWMANYPAGES; page++) {
					col = photosetsInterface.getPhotos(id /* photosetId */, PHOTOSPERPAGE, page);

					for (Photo argazkia : col) {
						saveImage(argazkia);
						System.out.println(argazkia.getTitle() + ": ");
						System.out.println(argazkia.getDescription() + ": ");
						System.out.println(argazkia.getDateAdded() + ": ");
						System.out.println(argazkia.getDatePosted() + ": ");
						System.out.println(argazkia.getDateTaken() + ": ");
						System.out.println(argazkia.getGeoData() + ": ");
						System.out.println(argazkia.getTags());
					}
				}
			}
		} catch (FlickrException e) {
			e.printStackTrace();
		}
	}

	// convert filename to clean filename
	public static String convertToFileSystemChar(String name) {
		String erg = "";
		Matcher m = Pattern.compile("[a-z0-9 _#&@\\[\\(\\)\\]\\-\\.]", Pattern.CASE_INSENSITIVE).matcher(name);
		while (m.find()) {
			erg += name.substring(m.start(), m.end());
		}
		if (erg.length() > 200) {
			erg = erg.substring(0, 200);
			System.out.println("cut filename: " + erg);
		}
		return erg;
	}

	@SuppressWarnings("deprecation")
	public boolean saveImage(Photo p) {

		String path = "pics" + File.separator;
		String cleanTitle = convertToFileSystemChar(p.getTitle());

		File orgFile = new File(path + File.separator + cleanTitle + "_" + p.getId() + "_o." + p.getOriginalFormat());
        File largeFile = new File(path + File.separator + cleanTitle + "_" + p.getId() + "_b." + p.getOriginalFormat());

        if (orgFile.exists() || largeFile.exists()) {
            System.out.println(p.getTitle() + "\t" + p.getLargeUrl() + " skipped!");
            return false;
        }

		try {
            Photo nfo = f.getPhotosInterface().getInfo(p.getId(), null);
            if (nfo.getOriginalSecret().isEmpty()) {
                ImageIO.write(p.getLargeImage(), p.getOriginalFormat(), largeFile);
                System.out.println(p.getTitle() + "\t" + p.getLargeUrl() + " was written to " + largeFile.getName());
            } else {
                p.setOriginalSecret(nfo.getOriginalSecret());
                ImageIO.write(p.getOriginalImage(), p.getOriginalFormat(), orgFile);
                System.out.println(p.getTitle() + "\t" + p.getOriginalUrl() + " was written to " + orgFile.getName());
            }
        } catch (FlickrException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return true;
	}
}