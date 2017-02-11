package scondor.deck.card.troops;

import scondor.deck.card.fcode.Target;
import scondor.mana.ManaType;

public class DefenseTroop extends TroopCardData{

	public DefenseTroop(int mana_cost, int attack, int live, ManaType mana_type, int fcode) {
		super(mana_cost, attack, live, mana_type, fcode);
	}

	@Override
	public void attack(Target t) {
		// TODO Auto-generated method stub
		
	}

}
