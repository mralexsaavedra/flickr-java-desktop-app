package flickr;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class ArgazkiakJaitsi {

	public static void main(String[] args) throws Exception {
		String imageUrl = "https://acufope.files.wordpress.com/2014/02/cmo-integrar-una-gal-1142778666.jpg";
		String destinationFile = "/Users/alexander/Downloads/image.jpg";

		saveImage(imageUrl, destinationFile);
	}

	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}

}
