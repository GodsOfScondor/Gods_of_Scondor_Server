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
import scondor.session.EndOfGameType;
import scondor.session.GameType;
import scondor.session.Lobby;
import scondor.session.SessionController;
import scondor.session.SessionMaster;
import scondor.shop.Shop;

public class Server extends ServerEventListener {

	@Override
	protected void clientConnected(ClientModel client) {
		Console.info(client.getUUID() + " has connected!");
	}

	@Override
	protected void clientDisconnected(ClientModel client) {
		Console.info(client.getUUID() + " has disconnected!");
		SessionMaster.closeSession(client.getUUID());
		Player player = PlayerMaster.getPlayerUUID(client.getUUID());
		Lobby.leaveQueue(player);
		PlayerMaster.remove(player);
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
		 * ignore if player is not in list
		 */
		Player player = PlayerMaster.getPlayerUUID(client.getUUID());
		if (player!=null) player.updateClient(client);
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
				Shop.buy(client, player.getData(), parts[1]);
			}
			
			/*
			 * player tries to get a starter deck
			 */
			else if (msg.startsWith("starter;")) {
				DeckStarter.give(client, Integer.parseInt(parts[1]));
			}
			
			/*
			 * player tries to get a starter deck
			 */
			else if (msg.startsWith("starter;")) {
				DeckStarter.give(client, Integer.parseInt(parts[1]));
			}
			
			/*
			 * player tries to make a fight action
			 */
			else if (msg.startsWith("fight;")) {
				
				if (parts.length<3) {
					Console.error("Too less agruments. ("+msg+")");
					return;
				}
				
				if (parts[1].equalsIgnoreCase("action")) {
					SessionController controller = SessionMaster.getSession(player.getData().getUsername());
					
					if (controller!=null) {
						
						if (controller.getPlayer().getData().getUsername().equals(player.getData().getUsername())) {
							
							/*
							 * player triggers action
							 */
							
							if (parts.length==3) {
								
								/*
								 * switches players
								 */
								if (parts[2].equalsIgnoreCase("switch")) {
									controller.update("player switch");
								}
								
								/*
								 * player surrenders
								 */
								if (parts[2].equalsIgnoreCase("surrender")) {
									controller.getEnemy().getClient().sendPacket(new Message("fight;exit;"+EndOfGameType.SURRENDER_WIN.toString().toUpperCase()));
								}
								
							}
							
						} else {
							Console.error("Enemy is on turn! ("+msg+")");
						}
						
					} else {
						Console.error("No session found! ("+msg+")");
					}
					
				}
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
					Lobby.search(client, GameType.valueOf(parts[2].toUpperCase()));
					break;
				case "exit":
					Lobby.leaveQueue(player);
					break;
				}
			}
			
			/*
			 * player requests his/her money
			 */
			else if (msg.startsWith("money")){
				client.sendPacket(new Message("money;"+player.getData().getMoney()));
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
					client.sendPacket(player.getData().generateDeckList("avaible decks"));
				}
			}
			
		}
		
	}
	
}
