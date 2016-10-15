package flickr;

import java.sql.*;

import javax.swing.JOptionPane;

public class MySQLdb {
	private String url = "jdbc:mysql://localhost:3306/FlickrBackup?autoReconnect=true&useSSL=false";
	private String user = "";
	private String passwd = "";
	private String driver = "com.mysql.cj.jdbc.Driver";
	private static MySQLdb niremysql;

	private Connection konexioa;

	public MySQLdb() {
		try {
			Class.forName(this.driver).newInstance();
			this.konexioa = DriverManager.getConnection(this.url, this.user, this.passwd);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	public static MySQLdb getMySQLdb() {
		return niremysql != null ? niremysql : (niremysql = new MySQLdb());
	}

	public ResultSet kontsultaExekutatu() throws Exception {
		String agindua = "SELECT * FROM FlickrBackup.erabiltzaileak;";
		ResultSet emaitza = null;
		try {
			Statement st = this.konexioa.createStatement();
			emaitza = st.executeQuery(agindua);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emaitza;
	}

	public void login(String email, String pasahitza) {
		try {
			ResultSet rs = kontsultaExekutatu();
			 while(rs.next()){
				 int valor = rs.getRow();
				 if(rs.getString(valor).equals(email)){
				 valor++;
				 if(rs.getString(valor).equals(pasahitza)){
				 JOptionPane.showMessageDialog(null, "Eres un usuario registrado");
				 }
				 else{
				 JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
				 }
				 break;
				 }
				 else{
				 JOptionPane.showMessageDialog(null, "No eres un usuario registrado");
				 }
				 break;
				 }
				  
				 konexioa.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}