package scondor;

import scondor.color.Color;
import scondor.deck.card.CardMaster;

public class ContentLoader {
	
	public static void load() {
		
		CardMaster.load();
		System.out.println(Color.INFO + "Loaded card lib!");
		
	}
	
}
