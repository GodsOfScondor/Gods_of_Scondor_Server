package scondor;


import java.util.Scanner;

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
		
		System.out.println(CMDTool.GREEN + " - Gods of Scondor | Server - ");
		
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
			System.out.println("WROTE: "+line);
			
			if(line.equals("help")){
				Console.info("Server-Commands:\n");
				System.out.println("help                      | Shows all available Commands");
				System.out.println("create_licenses_n         | Automatically generates n licenses\n");
			}
			
			if(line.startsWith("create_licenses_")){
				parts=line.split("_");
				LicenseCreator.generateLicenses(Integer.parseInt(parts[2]));
			}
		}
		s.close();
	}
	
	
}
