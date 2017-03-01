package scondor.deck.card.spell;

import scondor.deck.card.Card;
import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.FCode;
import scondor.deck.card.fcode.Vars;

public class SingleTargetSpell extends Card<SpellCardData> {

	public SingleTargetSpell(SpellCardData data, FCode fcode) {
		super(data,fcode);
	}

	@Override
	public void compile(Vars vars, String cmd, CompileData data) {
		
	}

}