package scondor.deck.card.fieldcard;

import scondor.deck.card.Card;
import scondor.deck.card.fcode.FCode;
import scondor.deck.card.fcode.Target;
import scondor.mana.ManaType;

public class FieldCard extends Card {

	public FieldCard(int mana_cost, ManaType mana_type,int fcode) {
		super(mana_cost, mana_type, fcode);
	}

	@Override
	public void compile(FCode fc, String cmd, Target target) {
		// TODO Auto-generated method stub
		
	}

}