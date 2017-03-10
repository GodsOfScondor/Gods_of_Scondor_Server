package scondor;

import scondor.deck.DeckLoader;
import scondor.deck.card.fcode.FCodeLoader;
import scondor.god.GodLoader;

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
		DeckLoader.load();
		Console.info("Loaded cards!");
		DeckLoader.load();
		Console.info("Loaded decks!");
		
	}
	
}
