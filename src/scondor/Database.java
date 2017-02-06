package scondor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import scondor.color.CMDTool;

public class Database {
	
	private static String username = "root";
	private static String password = "N3U3S_PASSWORT";
	private static String database = "Gods_of_Scondor";
	private static String host = "localhost";
	private static int port = 3306;
	private static Connection con;
	
	public static void setup() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (!isConnected()) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(CMDTool.ERROR + "Failed to connect to database!");
			}
			System.out.println(CMDTool.INFO + "Succesfully connected to database!");
			createTable("GENERAL", "LICENSE VARCHAR(24)", "NAME VARCHAR(10)", "PASSWORD VARCHAR(30)", "LEVEL INT(100)");
		}
	}
	
	public static void createTable(String name, String... params) {
		String parameter = "";
		for (String param : params) {
			if (!parameter.isEmpty()) parameter = parameter + ", ";
			parameter = parameter + param;
		}
		System.out.println("CREATE TABLE IF NOT EXISTS GOS_"+name + "(" + parameter + ")");
		execute("CREATE TABLE IF NOT EXISTS GOS_"+name + "(" + parameter + ")");
	}

	private static boolean isConnected() {
		return con!=null;
	}
	
	/**
	 * 
	 * pushes sql commands to server
	 * 
	 */
	public static void execute(String command) {
		if (isConnected()) try {
			con.createStatement().executeUpdate(command);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * pushes sql query commands to server with result
	 * 
	 */
	public static ResultSet query(String command) {
		if (isConnected())
		try {
			return con.createStatement().executeQuery(command);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * closes connection
	 * 
	 */
	public static void disconnect() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(CMDTool.ERROR+"Failed to close connection to database!");
		}
		System.out.println(CMDTool.INFO+"Closed connection to database!");
	}
	
}
