package scondor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	
	private static String username;
	private static String password;
	private static String database;
	private static String host;
	private static int port;
	private static Connection con;
	
	private static BufferedReader reader;
	private static String line;
	
	public static void setup() {
		/*
		 * 
		 */
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream("data/sql.dat"), "UTF-8"));
			
			while ((line=reader.readLine())!=null) {
				if(line.startsWith("name")) {
					username = line.split("=")[1];
				}
				if(line.startsWith("password")) {
					password = line.split("=")[1];
				}
				if(line.startsWith("database")) {
					database = line.split("=")[1];
				}
				if(line.startsWith("host")) {
					host = line.split("=")[1];
				}
				if(line.startsWith("port")) {
					port = Integer.parseInt(line.split("=")[1]);
				}
			}

			reader.close();
			
		} catch (UnsupportedEncodingException | FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * 
		 */
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
			Console.info("Succesfully connected to database!");
			createTable("USER", "ID INT AUTO_INCREMENT", "LICENSE VARCHAR(24)", "NAME VARCHAR(10)", "PASSWORD VARCHAR(30)", "PRIMARY KEY (ID)");
			createTable("PLAYER", "ID INT", "LEVEL INT(100)", "XP INT(100)", "MONEY INT(100)", "ELO INT(100)", "PRIMARY KEY (ID)");
			createTable("DECKS", "DECK_ID INT AUTO_INCREMENT", "ID INT(100)", "NAME VARCHAR(20)", "PRIMARY KEY (DECK_ID)");
			createTable("STATS", "ID INT", "WINS INT(100)", "LOSES INT(100)", "DRAWS INT(100)", "PRIMARY KEY (ID)");
			createTable("FREELICENSES", "LICENSE VARCHAR(24)");
		}
	}
	
	public static void createTable(String name, String... params) {
		String parameter = "";
		for (String param : params) {
			if (!parameter.isEmpty()) parameter = parameter + ", ";
			parameter = parameter + param;
		}
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
			System.out.println(CMDTool.SQL + command);
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
		if (isConnected()) try {
			System.out.println(CMDTool.SQL + command);
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
			Console.error("Failed to close connection to database!");
		}
		Console.info("Closed connection to database!");
	}
	
}
