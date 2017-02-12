package scondor;

import org.gnet.packet.Packet;
import org.gnet.server.ClientModel;
import org.gnet.server.ServerEventListener;

import scondor.packets.Authentication;
import scondor.packets.Message;
import scondor.packets.Verification;
import scondor.player.LicenseChecker;
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
			}
			// send player login failed msg
			else {
				System.out.println(CMDTool.WARN + client.getUuid() + ": " + (String)packet.getEntry("USERNAME") + "failed to login.");
				// TODO inform client
			}
		}
		
		/*
		 * player registers to server
		 */
		else if (packet instanceof Verification) {
			//only if license is right:
			Database.execute("INSERT INTO `Gods_of_Scondor`.`GOS_PLAYER` (`ID`, `LEVEL`, `XP`) VALUES (NULL, '1', '0');");
			Database.execute("INSERT INTO `Gods_of_Scondor`.`GOS_USER` (`ID`, `LICENSE`, `NAME`, `PASSWORD`) VALUES (NULL, '0000-0000-0000-0000-0000', 'Björnsen', 'ZuVielSwagAmBeen');");
		}
		
		/*
		 * player registers to server
		 */
		else if (packet instanceof Message) {
			System.out.println(CMDTool.INFO + (String) packet.getEntry("MESSAGE"));
		}
		
	}
	
}
