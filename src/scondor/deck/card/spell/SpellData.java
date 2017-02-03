package scondor.deck.card.spell;

import scondor.deck.card.CardData;
import scondor.mana.ManaType;

public abstract class SpellData extends CardData {

	public SpellData(int mana_cost, ManaType mana_type) {
		super(mana_cost, mana_type);
	}

}