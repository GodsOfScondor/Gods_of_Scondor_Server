package scondor;

import scondor.deck.DeckStarter;
import scondor.gnet.packet.Packet;
import scondor.gnet.server.ClientModel;
import scondor.gnet.server.ServerEventListener;
import scondor.licenses.LicenseChecker;
import scondor.packets.Authentication;
import scondor.packets.Message;
import scondor.packets.Verification;
import scondor.player.Player;
import scondor.player.PlayerMaster;
import scondor.player.Shop;
import scondor.session.GameType;
import scondor.session.Lobby;

public class Server extends ServerEventListener {

	@Override
	protected void clientConnected(ClientModel client) {
		Console.info(client.getUUID() + " has connected!");
	}

	@Override
	protected void clientDisconnected(ClientModel client) {
		Console.info(client.getUUID() + " has disconnected!");
		PlayerMaster.remove(PlayerMaster.getPlayerUUID(client.getUUID()));
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
		
		Player player = PlayerMaster.getPlayerUUID(client.getUUID());
		
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
		 * ignore if player is not in list
		 */
		if (player==null) return;
		
		/*
		 * player registers to server
		 */
		else if (packet instanceof Message) {
			
			String msg = (String) packet.getEntry("MESSAGE");
			String parts[] = msg.split(";");
			Console.info(msg);
			
			/*
			 * player tries to something
			 */
			if (msg.startsWith("buy;")) {
				Shop.buy(client, player.getData(), Integer.parseInt(parts[1]));
			}
			
			/*
			 * player tries to get a starter deck
			 */
			else if (msg.startsWith("starter;")) {
				DeckStarter.give(client, Integer.parseInt(parts[1]));
			}
			
			/*
			 * player tries to join/exit lobby
			 */
			else if (msg.startsWith("lobby;")) {
				if (parts.length<2) return;
				switch (parts[1]) {
				case "deck":
					int deck = Integer.parseInt(parts[2]);
					Lobby.join(player, deck);
					break;
				case "session":
					Lobby.search(player, GameType.valueOf(parts[2].toUpperCase()));
					break;
				case "exit":
					Lobby.leaveQueue(player);
					break;
				}
			}
			
			/*
			 * player tries to request data
			 */
			else if (msg.startsWith("request;")) {
				/*
				 * player tries to get a list of his/her cards
				 */
				if (parts[1].equalsIgnoreCase("cardlist")) {
					client.sendPacket(player.getData().getCards().generateCardList("avaible cards"));
				}
				if (parts[1].equalsIgnoreCase("decklist")) {
					client.sendPacket(player.getData().generateDeckList("avaible cards"));
				}
			}
			
		}
		
	}
	
}
