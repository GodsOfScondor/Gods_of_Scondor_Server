package scondor.deck.card.spell;

import scondor.deck.card.fcode.Target;
import scondor.mana.ManaType;

public class MultiTargetSpell extends SpellData {

	public MultiTargetSpell(int mana_cost, ManaType mana_type,int fcode) {
		super(mana_cost, mana_type,fcode);
	}

	@Override
	public void compile(String cmd, Target target) {
		// TODO Auto-generated method stub
		
	}


}