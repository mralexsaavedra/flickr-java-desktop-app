package flickrJava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photosets.Photoset;
import com.flickr4java.flickr.photosets.Photosets;
import com.flickr4java.flickr.photosets.PhotosetsInterface;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.flickr4java.flickr.uploader.Uploader;
import com.flickr4java.flickr.util.IOUtilities;

import kudeatzaileak.Kudeatzailea;

public class Argazkiak {

	static String apiKey;
	static String sharedSecret;
	Flickr f;
	REST rest;
	RequestContext requestContext;
	Properties properties = null;
	private String erabiltzaile;
	
	public Argazkiak(String email) throws IOException {
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
	
	public  List<String[]> showPhotos() {

		String userId = properties.getProperty("nsid");

		PhotosetsInterface photosetsInterface = f.getPhotosetsInterface();
		Photosets photosets;
		
		List<String[]> emaitza = new ArrayList<String[]>();

		try {
			photosets = photosetsInterface.getList(userId);

			Collection<Photoset> bildumak = photosets.getPhotosets();

			for (Photoset photoset : bildumak) {
				String id = photoset.getId();
				int photoCount = photoset.getPhotoCount();

				PhotoList<Photo> col;
				int PHOTOSPERPAGE = photoCount;
				int HOWMANYPAGES;
				if (photoCount>10)
					HOWMANYPAGES = (int) Math.ceil(photoCount / 10);
				else
					HOWMANYPAGES = 1;
				
				for (int page = 1; page <= HOWMANYPAGES; page++) {
					col = photosetsInterface.getPhotos(id /* photosetId */, PHOTOSPERPAGE, page);

					for (Photo argazkia : col) {
						String[] res = new String[8];
						res[0] = argazkia.getTitle();
						res[1] = photoset.getTitle();
						res[2] = argazkia.getDescription();
						res[3] = konprobatuData(argazkia.getDateAdded());
						res[4] = konprobatuData(argazkia.getDatePosted());
						res[5] = konprobatuData(argazkia.getDateTaken());
						res[6] = konprobatuData(argazkia.getGeoData());
						res[7] = argazkia.getTags().toString();
						emaitza.add(res);
					}
				}
			}
		} catch (FlickrException e) {
			e.printStackTrace();
		}
		return emaitza;
	}
	
	public String konprobatuData(Object o){
		if (o==null)
			return ("Ez dago");
		else
			return o.toString();
	}
	
	public void argazkiakGorde() throws Exception {
		String userId = properties.getProperty("nsid");

		PhotosetsInterface photosetsInterface = f.getPhotosetsInterface();
		Photosets photosets;
		try {
			photosets = photosetsInterface.getList(userId);

			Collection<Photoset> bildumak = photosets.getPhotosets();

			for (Photoset photoset : bildumak) {
				String id = photoset.getId();
				int photoCount = photoset.getPhotoCount();

				PhotoList<Photo> col;
				int PHOTOSPERPAGE = photoCount;
				int HOWMANYPAGES;
				if (photoCount>10)
					HOWMANYPAGES = (int) Math.ceil(photoCount / 10);
				else
					HOWMANYPAGES = 1;
				
				for (int page = 1; page <= HOWMANYPAGES; page++) {
					col = photosetsInterface.getPhotos(id /* photosetId */, PHOTOSPERPAGE, page);

					for (Photo argazkia : col) {
						saveImage(argazkia);
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
	public boolean saveImage(Photo p) throws Exception {
		
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
            
    			String deskripzioa = konprobatuData(p.getDescription());
			String dateAdded = konprobatuData(p.getDateAdded());
			String datePosted = konprobatuData(p.getDatePosted());
			String dateTaken = konprobatuData(p.getDateTaken());
			String geoData = konprobatuData(p.getGeoData());
			//MD5 md5 = new MD5();
			//Tag argakiMD5 = md5.MD5CheckSum(orgFile);
			//p.getTags().add(argakiMD5);
			String tag = p.getTags().toString();
			
    			Kudeatzailea.getInstantzia().argazkiakGorde(p.getId(), p.getTitle(),orgFile.toString(), deskripzioa,dateAdded,datePosted,dateTaken,geoData,tag);
        } catch (FlickrException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return true;
	}
	
	public  void erlazioakGordeDB() {

		String userId = properties.getProperty("nsid");

		PhotosetsInterface photosetsInterface = f.getPhotosetsInterface();
		Photosets photosets;
		
		try {
			photosets = photosetsInterface.getList(userId);

			Collection<Photoset> bildumak = photosets.getPhotosets();

			for (Photoset photoset : bildumak) {
				String id = photoset.getId();
				int photoCount = photoset.getPhotoCount();

				PhotoList<Photo> col;
				int PHOTOSPERPAGE = photoCount;
				int HOWMANYPAGES;
				if (photoCount>10)
					HOWMANYPAGES = (int) Math.ceil(photoCount / 10);
				else
					HOWMANYPAGES = 1;
				
				for (int page = 1; page <= HOWMANYPAGES; page++) {
					col = photosetsInterface.getPhotos(id /* photosetId */, PHOTOSPERPAGE, page);

					for (Photo argazkia : col) {
						Kudeatzailea.getInstantzia().erlazioakEgin(erabiltzaile, photoset.getId(), argazkia.getId());
					}
				}
			}
		} catch (FlickrException e) {
			e.printStackTrace();
		}
	}
	
	public void igoArgazkia(File imageFile, String bildumaID) throws IOException, FlickrException{
		InputStream in = null;
		Uploader uploader = f.getUploader();
		PhotosInterface pint = f.getPhotosInterface();
		try {
			in = new FileInputStream(imageFile);
			UploadMetaData metaData = buildPrivatePhotoMetadata();
			metaData.setPublicFlag(true);
			metaData.setTitle(imageFile.getName());
			String photoId = uploader.upload(in, metaData);
			PhotosetsInterface iface = f.getPhotosetsInterface();
	        iface.addPhoto(bildumaID, photoId);
	        Photo photo = pint.getPhoto(photoId);
	        try {
				this.saveImage(photo);
				Kudeatzailea.getInstantzia().erlazioakEgin(erabiltzaile, bildumaID, photoId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} finally {
			IOUtilities.close(in);
		}
	}
	
	public UploadMetaData buildPrivatePhotoMetadata() {
		UploadMetaData uploadMetaData = new UploadMetaData();
		uploadMetaData.setPublicFlag(false);
		return uploadMetaData;
	}
	
}