package scondor;

import scondor.deck.DeckLoader;
import scondor.deck.card.CardMaster;
import scondor.deck.card.fcode.FCodeReader;

public class ContentLoader {
	
	public static void load() {
		
		
		FCodeReader.load();
		System.out.println(CMDTool.INFO + "Loaded FCodes!");
		CardMaster.load();
		System.out.println(CMDTool.INFO + "Loaded cards!");
		DeckLoader.load();
		System.out.println(CMDTool.INFO + "Loaded decks!");
			
	}
	
}
