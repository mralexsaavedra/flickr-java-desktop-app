package flickrJava;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class MD5{
	
	private FileInputStream fis;

	public MD5(){
		
	}

	public String MD5Hashing(String password) throws Exception {		
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
	
	public String MD5CheckSum(File file) throws Exception{
        MessageDigest md = MessageDigest.getInstance("MD5");
        fis = new FileInputStream(file);

        byte[] dataBytes = new byte[1024];

        int nread = 0;
        while ((nread = fis.read(dataBytes)) != -1) {
          md.update(dataBytes, 0, nread);
        };
        byte[] mdbytes = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
	
	
}
