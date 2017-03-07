package scondor;

import scondor.deck.DeckLoader;
import scondor.deck.card.CardLoader;
import scondor.deck.card.fcode.FCodeReader;

public class ContentLoader {
	
	public static void load() {
		
		
		FCodeReader.load();
		Console.info("Loaded FCodes!");
		CardLoader.load();
		Console.info("Loaded cards!");
		DeckLoader.load();
		Console.info("Loaded decks!");
		
		/*
		 *Testcode 
		 */
		/*Card at=new AttackTroop(0, 0, 0, ManaType.FIRE, 2, 0);
		Card de=new DefenseTroop(0, 0, 0, ManaType.FIRE,1);
		Card sp=new SingleTargetSpell(0,ManaType.FIRE, 2);
		Card fc=new AttackTroop(2,2,2, ManaType.WATER, 2, 0);
		at.triggerFCode(new Target(fc, TargetType.SINGLE_TARGET));
		de.triggerFCode(new Target(fc, TargetType.SINGLE_TARGET));
		sp.triggerFCode(new Target(fc, TargetType.SINGLE_TARGET));
		*/
	}
	
}
