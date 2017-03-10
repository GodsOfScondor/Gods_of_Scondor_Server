package scondor;

import scondor.deck.DeckLoader;
import scondor.deck.card.CardLoader;
import scondor.deck.card.fcode.FCodeLoader;
import scondor.god.GodLoader;
import scondor.player.PlayerMaster;

public class ContentLoader {
	
	/**
	 * 
	 * starts all loader
	 * 
	 */
	public static void load() {
		
		Console.send("");
		Console.info("Starting content loading...");
		FCodeLoader.load();
		Console.info("Loaded fcodes!");
		GodLoader.load();
		Console.info("Loaded gods!");
		CardLoader.load();
		Console.info("Loaded cards!");
		DeckLoader.load();
		Console.info("Loaded decks!\n");
		
	}
	
	public static void save() {
		
		Console.send("");
		Console.info("Starting content saving...");
		PlayerMaster.saveAll();
		Console.info("Saved player cards!");
		DeckLoader.saveAll();
		Console.info("Saved decks!\n");
		
	}
	
}
