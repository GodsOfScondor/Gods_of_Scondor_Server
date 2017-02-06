package scondor;


import org.gnet.server.GNetServer;

import scondor.color.CMDTool;

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
		
	}
	
}
