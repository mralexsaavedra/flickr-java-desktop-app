package kudeatzaileak;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Kudeatzailea {

	private static final Kudeatzailea kudeatzaile = new Kudeatzailea();

	public static Kudeatzailea getInstantzia() {
		return kudeatzaile;
	}

	private Kudeatzailea() {
		// Singleton patroia
	}

	public List<String[]> getErabiltzaileak() {

		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		ResultSet rs = dbkud.execSQL("SELECT * from erabiltzaile");
		List<String[]> emaitza = new ArrayList<String[]>();
		try {
			while (rs.next()) {
				String[] res = new String[2];
				res[0] = rs.getString("email");
				res[1] = rs.getString("pasahitza");
				emaitza.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emaitza;
	}
	
	public List<String[]> getBildumak(String email){

		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		ResultSet rs = dbkud.execSQL("SELECT * from bilduma WHERE erabiltzaileEmail='" + email + "';");
		List<String[]> emaitza = new ArrayList<String[]>();
		try {
			while (rs.next()) {
				String[] res = new String[3];
				res[0] = rs.getString("izena");
				res[1] = rs.getString("deskripzioa");
				res[2] = rs.getString("argazkiKopurua");
				emaitza.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emaitza;
	}
	
	public List<String[]> getArgazkiak(){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		ResultSet rs = dbkud.execSQL("SELECT md5,izena,file from argazki");
		List<String[]> emaitza = new ArrayList<String[]>();
		try {
			while (rs.next()) {
				String[] res = new String[3];
				res[0] = rs.getString("md5");
				res[1] = rs.getString("izena");
				res[2] = rs.getString("file");
				emaitza.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emaitza;
	}
	
	public String getArgazki(String argazkia){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		ResultSet rs = dbkud.execSQL("SELECT md5 FROM argazki WHERE izena='" + argazkia + "';");
		String emaitza = null;
		try {
			while (rs.next()) {
				emaitza = rs.getString("md5");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emaitza;
	}
	
	public String getArgazkiFile(String argazkia){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		ResultSet rs = dbkud.execSQL("SELECT file FROM argazki WHERE izena='" + argazkia + "';");
		String emaitza = null;
		try {
			while (rs.next()) {
				emaitza = rs.getString("file");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emaitza;
	}
	
	public List<String> getBildumaFile(String bilduma, String email){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		ResultSet rs = dbkud.execSQL("SELECT a.file FROM argazki a, erlazioa e "
				+ "WHERE a.md5=e.argazkia AND e.bilduma='" + bilduma + "' AND e.erabiltzailea='" + email + "';");
		List<String> emaitza = new ArrayList<String>();
		try {
			while (rs.next()) {
				String res = new String();
				res = rs.getString("file");
				emaitza.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emaitza;
	}
	
	public List<String[]> getBildumaBatenArgazkia(String bilduma){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		ResultSet rs = dbkud.execSQL("SELECT a.izena FROM argazki a, erlazioa e WHERE a.md5=e.argazkia AND e.bilduma='" + bilduma + "';");
		List<String[]> emaitza = new ArrayList<String[]>();
		try {
			while (rs.next()) {
				String[] res = new String[1];
				res[0] = rs.getString("izena");
				emaitza.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emaitza;
	}
	
	public List<String[]> getErlazioak(String email){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		ResultSet rs = dbkud.execSQL("SELECT a.izena,e.bilduma,a.deskripzioa,a.dateAdded,a.datePosted,a.dateTaken,a.geoData,a.tag FROM erlazioa e,argazki a WHERE e.argazkia=a.md5 AND erabiltzailea='" + email + "';");
		List<String[]> emaitza = new ArrayList<String[]>();
		try {
			while (rs.next()) {
				String[] res = new String[8];
				res[0] = rs.getString("izena");
				res[1] = rs.getString("bilduma");
				res[2] = rs.getString("deskripzioa");
				res[3] = rs.getString("dateAdded");
				res[4] = rs.getString("datePosted");
				res[5] = rs.getString("dateTaken");
				res[6] = rs.getString("geoData");
				res[7] = rs.getString("tag");
				emaitza.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emaitza;
	}
	
	public  void bildumakGorde(String izena, String deskripzioa, int argazkiKop, String email){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		String kontsulta = "INSERT INTO bilduma (izena,deskripzioa,argazkiKopurua,erabiltzaileEmail) VALUES ('" + izena + "','" + deskripzioa + "'"
									+ ",'" + argazkiKop  + "','" + email + "');";
		System.out.println(kontsulta);
		dbkud.execSQL(kontsulta);
	}
	
	public void argazkiakGorde(String md5, String izena, String file, String deskripzioa, String dateAdded, String datePosted, String dateTaken, String geoData, String tag){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		String kontsulta = "INSERT INTO argazki (md5,izena,file,deskripzioa,dateAdded,datePosted,dateTaken,geoData,tag) VALUES ('" + md5 + "','" + izena + "','" + file + "','" + deskripzioa + "','" + dateAdded + "','" + datePosted + "','" + dateTaken + "','" + geoData + "','" + tag + "');";
		System.out.println(kontsulta);
		dbkud.execSQL(kontsulta);
	}
	
	public void erlazioakEgin(String erabiltzailea, String bilduma, String argazkia){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		String kontsulta = "INSERT INTO erlazioa (erabiltzailea,bilduma,argazkia) VALUES ('" + erabiltzailea + "','" + bilduma + "','" + argazkia + "');";
		System.out.println(kontsulta);
		dbkud.execSQL(kontsulta);
	}
	
}
