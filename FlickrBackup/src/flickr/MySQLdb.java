package flickr;

import java.sql.*;

public class MySQLdb {
	private String url = "jdbc:mysql://localhost:3306/";
    private String user = "root"; 
    private String passwd = "asg696ASG";
	private String driver = "com.mysql.jdbc.Driver";
	private static MySQLdb niremysql;
	
    private Connection conn;
	
	private MySQLdb() {
		try {
        	Class.forName(this.driver).newInstance();
        	this.conn = DriverManager.getConnection(this.url,this.user,this.passwd);
    	} catch(Exception e) {
        	System.out.println("Exception: " + e.getMessage());
    	}
	}
	
	public static MySQLdb getMySQLdb(){
		return niremysql!=null ? niremysql : (niremysql = new MySQLdb());
	}
	
	
}