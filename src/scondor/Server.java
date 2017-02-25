package scondor;

import scondor.gnet.packet.Packet;
import scondor.gnet.server.ClientModel;
import scondor.gnet.server.ServerEventListener;
import scondor.licenses.LicenseChecker;
import scondor.packets.Authentication;
import scondor.packets.Message;
import scondor.packets.Verification;
import scondor.player.PlayerMaster;

public class Server extends ServerEventListener {

	@Override
	protected void clientConnected(ClientModel client) {
		Console.info(client.getUUID() + " has connected!");
	}

	@Override
	protected void clientDisconnected(ClientModel client) {
		Console.info(client.getUUID() + " has disconnected!");
		PlayerMaster.remove(PlayerMaster.getPlayer(client.getUUID()));
	}

	@Override
	protected void debugMessage(String msg) {
		System.out.println(msg);
	}

	@Override
	protected void errorMessage(String msg) {
		System.out.println(msg);
	}
	
	/**
	 * 
	 * client listener
	 * 
	 */
	@Override
	protected void packetReceived(ClientModel client, Packet packet) {
		
		/*
		 * player logs into server
		 */
		if (packet instanceof Authentication) {
			// check players authentication
			LicenseChecker.login(
					client,
					(String)packet.getEntry("USERNAME"),
					(String) packet.getEntry("PASSWORD")
				);
		}
		
		/*
		 * player registers to server
		 */
		else if (packet instanceof Verification) {
			LicenseChecker.register(
					client,
					(String)packet.getEntry("USERNAME"),
					(String) packet.getEntry("PASSWORD"),
					(String) packet.getEntry("LICENSE")
				);
		}
		
		/*
		 * player registers to server
		 */
		else if (packet instanceof Message) {
			Console.info((String) packet.getEntry("MESSAGE"));
		}
		
	}
	
}
