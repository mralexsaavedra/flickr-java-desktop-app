package flickr;

import java.sql.ResultSet;

public class Operazioak {
	
	public String getPassword(String pasahitza) {
		String query = "SELECT password FROM FlickrBackup.erabiltzaileak WHERE pasahitza='" + pasahitza + "';";
		System.out.println("     DB query: " + query);
		String password = null;
    	try {
	    	ResultSet res = MySQLdb.getMySQLdb().kontsultaExekutatu(query);
	    	while(res.next()) {
	    		password = res.getString("pasahitza");
        	}
        } catch(Exception e) {
        	System.out.println("Exception: " + e.getMessage());
    	}
    	return password;
	}
	
	public String getEmail(String email) {
		String query = "SELECT email FROM FlickrBackup.erabiltzaileak WHERE email='" + email + "';";
		System.out.println("     DB query: " + query);
		String nireEmail = null;
    	try {
	    	ResultSet res = MySQLdb.getMySQLdb().kontsultaExekutatu(query);
	    	while(res.next()) {
	    		nireEmail = res.getString("email");
        	}
        } catch(Exception e) {
        	System.out.println("Exception: " + e.getMessage());
    	}
    	return nireEmail;
	}
		
}
