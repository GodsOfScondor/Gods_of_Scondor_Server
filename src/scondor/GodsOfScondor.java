package scondor;


import scondor.gnet.server.GNetServer;
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
		server.setDebugging(false);
		
		Database.setup();
		
		ContentLoader.load();
		
		SessionMaster.init();
		
		Console.listen();
		
	}
	
	
}
