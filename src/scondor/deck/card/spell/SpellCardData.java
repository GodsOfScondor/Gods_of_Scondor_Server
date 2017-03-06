package scondor.deck.card.spell;

import scondor.deck.card.CardData;
import scondor.mana.ManaType;

public class SpellCardData extends CardData {

	private static final long serialVersionUID = -8000955067669392632L;

	public SpellCardData(String name, String description, int mana_cost, ManaType mana_type) {
		super(name, description, mana_cost, mana_type);
	}

}
