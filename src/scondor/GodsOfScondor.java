package scondor;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import scondor.deck.card.fcode.FCodeLoader;
import scondor.gnet.server.GNetServer;
import scondor.licenses.LicenseCreator;
import scondor.session.SessionMaster;

public class GodsOfScondor {
	
	private static final String IP = "84.200.106.98";
	private static final int PORT = 2882;
	
	/**
	 * 
	 * @author i
	 * 
	 */
	public static void main(String[] args) {
		
		System.out.println(CMDTool.GREEN + "\n\n - Gods of Scondor | Server - \n\n");
		
		GNetServer server = new GNetServer(IP, PORT);
		Console.info("Trying to start server...");
		server.bind();
		server.start();
		server.addEventListener(new Server());
		
		Database.setup();
		
		ContentLoader.load();
		
		SessionMaster.init();
		
		/*
		 * read console commands
		 */
		Scanner s = new Scanner(System.in);
		
		String line;
		String[] parts;
		
		while ((line = s.next())!=null) {
			
			if(line.equals("help")){
				Console.info("Server-Commands:\n");
				System.out.println("help                      | Shows all available Commands");
				System.out.println("create_licenses_n         | Automatically generates n licenses");
				System.out.println("delete_name               | Deletes user from database.");
				System.out.println("save                      | Saves contents.\n\n");
			}
			
			if(line.startsWith("create_licenses_")){
				parts=line.split("_");
				LicenseCreator.generateLicenses(Integer.parseInt(parts[2]));
			} else if(line.startsWith("execute_")){
				parts=line.split("_");
				FCodeLoader.getFCode(Integer.parseInt(parts[1])).execute(null, null);
			} else if(line.startsWith("delete_")){
				parts=line.split("_");
				ResultSet result = Database.query("SELECT ID FROM GOS_USER WHERE NAME='"+parts[1]+"'");
				int id = -1;
				try { while (result.next()) id = result.getInt("id");}
				catch (SQLException e) { e.printStackTrace(); }
				System.out.println(id);
				Database.execute("DELETE FROM `GOS_USER` WHERE ID='"+id+"'");
				Database.execute("DELETE FROM `GOS_PLAYER` WHERE ID='"+id+"'");
				Database.execute("DELETE FROM `GOS_DECKS` WHERE ID='"+id+"'");
				Database.execute("DELETE FROM `GOS_STATS` WHERE ID='"+id+"'");
			} else if(line.startsWith("save")){
				ContentLoader.save();
			}
		}
		s.close();
	}
	
	
}
