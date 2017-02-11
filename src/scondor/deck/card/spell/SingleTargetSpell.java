package scondor.deck.card.spell;

import scondor.deck.card.fcode.Target;
import scondor.mana.ManaType;

public class SingleTargetSpell extends SpellData{

	public SingleTargetSpell(int mana_cost, ManaType mana_type) {
		super(mana_cost, mana_type);
	}

	@Override
	public void attack(Target t) {
		// TODO Auto-generated method stub
		
	}

}