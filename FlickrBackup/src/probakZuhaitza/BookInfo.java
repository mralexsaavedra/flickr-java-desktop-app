package probakZuhaitza;

import java.net.URL;

public class BookInfo {
	public String bookName;
	public URL bookURL;

	public BookInfo(String book, String filename) {
		bookName = book;
		bookURL = getClass().getResource(filename);
		if (bookURL == null) {
			System.err.println("Couldn't find file: " + filename);
		}
	}

	public String toString() {
		return bookName;
	}
}