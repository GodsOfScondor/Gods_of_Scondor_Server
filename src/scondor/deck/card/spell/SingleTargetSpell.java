package scondor.deck.card.spell;

import scondor.deck.card.fcode.Target;
import scondor.mana.ManaType;

public class SingleTargetSpell extends SpellData{

	public SingleTargetSpell(int mana_cost, ManaType mana_type,int fcode) {
		super(mana_cost, mana_type,fcode);
	}

	@Override
	public void compile(String cmd, Target target) {
		// TODO Auto-generated method stub
		
	}

}