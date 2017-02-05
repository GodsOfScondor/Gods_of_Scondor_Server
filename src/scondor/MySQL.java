package scondor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
	
	public static String username = "root";
	public static String password = "N3U3S_PASSWORT";
	public static String database = "Gods_of_Scondor";
	public static String host = "localhost";
	public static int port = 3306;
	public static Connection con;
	
	public static void setup() {
		if (!isConnected()) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static boolean isConnected() {
		return con!=null;
	}
	
}
