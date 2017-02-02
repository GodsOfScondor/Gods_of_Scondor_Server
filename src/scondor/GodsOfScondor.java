package scondor;


import org.gnet.server.GNetServer;

import scondor.color.Color;

public class GodsOfScondor {
	
	private static final String IP = "84.200.106.98";
	private static final int PORT = 2882;
	
	public static void main(String[] args) {
		
		System.out.println(Color.GREEN + " - Gods of Scondor | Server - ");
		
		GNetServer server = new GNetServer(IP, PORT);
		server.bind();
		server.start();
		server.addEventListener(new Server());
		System.out.println(Color.INFO + "Trying to start server...");
		
	}
	
}
