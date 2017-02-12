package scondor;

import scondor.deck.card.CardMaster;

public class ContentLoader {
	
	public static void load() {
		
		CardMaster.load();
		System.out.println(CMDTool.INFO + "Loaded card lib!");
		
	}
	
}
