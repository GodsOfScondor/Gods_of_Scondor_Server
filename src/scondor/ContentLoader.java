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
