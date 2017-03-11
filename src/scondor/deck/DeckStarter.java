package scondor.deck;

import java.util.List;

import scondor.gnet.server.ClientModel;
import scondor.packets.Message;
import scondor.player.PlayerData;
import scondor.player.PlayerMaster;

public class DeckStarter {
	
	private static final int ALREADY_HAS_CARDS = 7;
	
	public static void give(ClientModel client, int deck) {
		
		PlayerData data = PlayerMaster.getPlayerUUID(client.getUUID()).getData();
		
		/*
		 * player has no cards
		 */
		if (data.getCards().getCards().size()==0) {
			
			List<DeckData> starter_decks = DeckLoader.getDecks(1);
			for (DeckData deckdata : starter_decks) {
				if (deckdata.getID()==deck) {
					DeckLoader.cloneDeck(deckdata.getID(), data.getPlayerID(), "Starterdeck");
				}
			}
			
		} else {
			
			/*
			 * user already has starter deck
			 */
			client.sendPacket(new Message(""+ALREADY_HAS_CARDS));
			
		}
		
	}
	
}
