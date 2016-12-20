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
		ResultSet rs = dbkud.execSQL("SELECT * FROM erabiltzaile");
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
		ResultSet rs = dbkud.execSQL("SELECT * FROM bilduma WHERE erabiltzaileEmail='" + email + "';");
		List<String[]> emaitza = new ArrayList<String[]>();
		try {
			while (rs.next()) {
				String[] res = new String[4];
				res[0] = rs.getString("id");
				res[1] = rs.getString("titulua");
				res[2] = rs.getString("deskripzioa");
				res[3] = rs.getString("argazkiKopurua");
				emaitza.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emaitza;
	}
	
	public String getBilduma(String bilduma){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		ResultSet rs = dbkud.execSQL("SELECT id FROM bilduma WHERE titulua='" + bilduma + "';");
		String emaitza = null;
		try {
			while (rs.next()) {
				emaitza = rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emaitza;
	}
	
	public List<String[]> getArgazkiak(){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		ResultSet rs = dbkud.execSQL("SELECT * FROM argazki");
		List<String[]> emaitza = new ArrayList<String[]>();
		try {
			while (rs.next()) {
				String[] res = new String[3];
				res[0] = rs.getString("id");
				res[1] = rs.getString("izena");
				res[2] = rs.getString("file");
				res[3] = rs.getString("deskripzioa");
				res[4] = rs.getString("dateAdded");
				res[5] = rs.getString("datePosted");
				res[6] = rs.getString("dateTaken");
				res[7] = rs.getString("geoData");
				res[8] = rs.getString("tag");
				emaitza.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emaitza;
	}
	
	public String getArgazki(String argazkia){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		ResultSet rs = dbkud.execSQL("SELECT id FROM argazki WHERE izena='" + argazkia + "';");
		String emaitza = null;
		try {
			while (rs.next()) {
				emaitza = rs.getString("id");
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
	
	public List<String> getBildumaFile(String bildumaID, String email){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		ResultSet rs = dbkud.execSQL("SELECT a.file FROM argazki a, erlazioa e "
				+ "WHERE a.id=e.argazkiID AND e.bildumaID='" + bildumaID + "' AND e.erabiltzailea='" + email + "';");
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
	
	public List<String[]> getBildumaBatenArgazkiak(String bildumaID){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		ResultSet rs = dbkud.execSQL("SELECT a.izena FROM argazki a, erlazioa e WHERE a.id=e.argazkiID AND e.bildumaID='" + bildumaID + "';");
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
	
	public List<String[]> getBildumaBatenIDGuztiak(String bildumaID){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		ResultSet rs = dbkud.execSQL("SELECT a.id FROM argazki a, erlazioa e WHERE a.id=e.argazkiID AND e.bildumaID='" + bildumaID + "';");
		List<String[]> emaitza = new ArrayList<String[]>();
		try {
			while (rs.next()) {
				String[] res = new String[1];
				res[0] = rs.getString("id");
				emaitza.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emaitza;
	}
	
	
	public List<String[]> getErlazioak(String email){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		ResultSet rs = dbkud.execSQL("SELECT a.izena,b.titulua,a.deskripzioa,a.dateAdded,a.datePosted,a.dateTaken,a.geoData,a.tag FROM erlazioa e,argazki a,bilduma b WHERE e.argazkiID=a.id AND e.bildumaID=b.id AND erabiltzailea='" + email + "';");
		List<String[]> emaitza = new ArrayList<String[]>();
		try {
			while (rs.next()) {
				String[] res = new String[8];
				res[0] = rs.getString("izena");
				res[1] = rs.getString("titulua");
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
	
	public List<String[]> getDatuBasekoEralzioak(String erabiltzaile){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		ResultSet rs = dbkud.execSQL("SELECT erabiltzailea,argazkiID,bildumaID FROM erlazioa WHERE erabiltzailea='" + erabiltzaile + "';");
		List<String[]> emaitza = new ArrayList<String[]>();
		try {
			while (rs.next()) {
				String[] res = new String[3];
				res[0] = rs.getString("erabiltzailea");
				res[1] = rs.getString("argazkiID");
				res[2] = rs.getString("bildumaID");
				emaitza.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emaitza;
	}
	
	public  void bildumakGorde(String id, String titulua, String deskripzioa, int argazkiKop, String email){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		String kontsulta = "INSERT INTO bilduma (id,titulua,deskripzioa,argazkiKopurua,erabiltzaileEmail) VALUES ('" + id + "','" + titulua + "','" + deskripzioa + "'"
									+ ",'" + argazkiKop  + "','" + email + "');";
		System.out.println(kontsulta);
		dbkud.execSQL(kontsulta);
	}
	
	public void argazkiakGorde(String id, String izena, String file, String deskripzioa, String dateAdded, String datePosted, String dateTaken, String geoData, String tag){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		String kontsulta = "INSERT INTO argazki (id,izena,file,deskripzioa,dateAdded,datePosted,dateTaken,geoData,tag) VALUES ('" + id + "','" + izena + "','" + file + "','" + deskripzioa + "','" + dateAdded + "','" + datePosted + "','" + dateTaken + "','" + geoData + "','" + tag + "');";
		System.out.println(kontsulta);
		dbkud.execSQL(kontsulta);
	}
	
	public void erlazioakEgin(String erabiltzailea, String bildumaID, String argazkiID){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		String kontsulta = "INSERT INTO erlazioa (erabiltzailea,argazkiID,bildumaID) VALUES ('" + erabiltzailea + "','" + argazkiID + "','" + bildumaID + "');";
		System.out.println(kontsulta);
		dbkud.execSQL(kontsulta);
	}
	
	public void erabiltzaileaGehitu(String email, String pasahitza){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		String kontsulta = "INSERT INTO erabiltzaile (email,pasahitza) VALUES ('" + email + "','" + pasahitza + "');";
		System.out.println(kontsulta);
		dbkud.execSQL(kontsulta);
	}
	
	public void deleteErlazioak(){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		String kontsulta = "DELETE FROM erlazioa;";
		dbkud.execSQL(kontsulta);
	}
	
	public void deleteBildumak(){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		String kontsulta = "DELETE FROM bilduma;";
		dbkud.execSQL(kontsulta);
	}
	
	public void deleteArgazkiak(){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		String kontsulta = "DELETE FROM argazki;";
		dbkud.execSQL(kontsulta);
	}
	
	public void updateBildumak(String titulua, String deskripzioa, String erabiltzaile){
		DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
		String kontsulta = "UPDATE bilduma SET titulua='" + titulua + "',deskripzioa='" + deskripzioa + "' WHERE erabiltzaileEmail='" + erabiltzaile + "';";
		dbkud.execSQL(kontsulta);
	}
}
