package scondor;

import scondor.gnet.packet.Packet;
import scondor.gnet.server.ClientModel;
import scondor.gnet.server.ServerEventListener;
import scondor.licenses.LicenseChecker;
import scondor.packets.Authentication;
import scondor.packets.Message;
import scondor.packets.Verification;
import scondor.player.Player;
import scondor.player.PlayerData;
import scondor.player.PlayerMaster;

public class Server extends ServerEventListener {

	@Override
	protected void clientConnected(ClientModel client) {
		System.out.println(CMDTool.INFO + " " + client.getUuid() + " has connected!");
	}

	@Override
	protected void clientDisconnected(ClientModel client) {
		System.out.println(CMDTool.INFO + " " + client.getUuid() + " has disconnected!");
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
			PlayerData data = LicenseChecker.login(
					(String)packet.getEntry("USERNAME"),
					(String) packet.getEntry("PASSWORD")
				);
			// add player to others...
			if (data!=null) {
				PlayerMaster.add(new Player(client, data));
				System.out.println(CMDTool.INFO + client.getUuid() + ": " + (String) packet.getEntry("USERNAME") + " has succesfully logged in.");
				client.sendPacket(new Message("msg:0,1,0:0:Succesfully logged in!"));
			}
			// send player login failed msg
			else {
				System.out.println(CMDTool.WARN + client.getUuid() + ": " + (String)packet.getEntry("USERNAME") + " failed to login.");
				client.sendPacket(new Message("msg:1,0,0:Failed to login!"));
			}
		}
		
		/*
		 * player registers to server
		 */
		else if (packet instanceof Verification) {
			if (!LicenseChecker.register(
					(String)packet.getEntry("USERNAME"),
					(String) packet.getEntry("PASSWORD"),
					(String) packet.getEntry("LICENSE")
				)) {
				System.out.println(CMDTool.WARN + client.getUuid() + ": " + (String)packet.getEntry("USERNAME") + " failed to register.");
				client.sendPacket(new Message("msg:1,0,0:Invalid license!"));
			}
		}
		
		/*
		 * player registers to server
		 */
		else if (packet instanceof Message) {
			System.out.println(CMDTool.INFO + (String) packet.getEntry("MESSAGE"));
		}
		
	}
	
}
