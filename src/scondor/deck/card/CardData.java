package scondor.deck.card;

import java.io.Serializable;

import scondor.mana.ManaType;

public abstract class CardData implements Serializable {
	
	private static final long serialVersionUID = 5034278386011906460L;
	
	protected String name;
	protected String description;
	protected int mana_cost;
	protected ManaType mana_type;
	
	public CardData(String name, String description, int mana_cost, ManaType mana_type) {
		this.name = name;
		this.description = description;
		this.mana_cost = mana_cost;
		this.mana_type = mana_type;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getMana_cost() {
		return mana_cost;
	}

	public ManaType getMana_type() {
		return mana_type;
	}
	
}
