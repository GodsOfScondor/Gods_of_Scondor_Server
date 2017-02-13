package scondor.deck.card.spell;

import scondor.deck.card.Card;
import scondor.mana.ManaType;

public abstract class SpellData extends Card {

	public SpellData(int mana_cost, ManaType mana_type,int fcode) {
		super(mana_cost, mana_type,fcode);
	}

}