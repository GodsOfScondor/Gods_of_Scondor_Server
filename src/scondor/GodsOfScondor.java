package scondor;


import java.util.Scanner;

import org.gnet.server.GNetServer;

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
		System.out.println(CMDTool.INFO + "Trying to start server...");
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
			if(line.startsWith("create_licenses_")){
				parts=line.split("_");
				LicenseCreator.generateLicenses(Integer.parseInt(parts[2]));
			}
		}
		s.close();
	}
	
}
