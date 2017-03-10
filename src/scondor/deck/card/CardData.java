package scondor.deck.card;

import java.io.Serializable;

import scondor.mana.ManaType;

public abstract class CardData implements Serializable {
	
	private static final long serialVersionUID = 5034278386011906460L;
	
	private int id;
	private int tex;
	protected String name;
	protected String description;
	protected int mana_cost;
	protected ManaType mana_type;
	
	public CardData(int id, int tex, String name, String description, int mana_cost, ManaType mana_type) {
		this.id = id;
		this.tex = tex;
		this.name = name;
		this.description = description;
		this.mana_cost = mana_cost;
		this.mana_type = mana_type;
	}
	
	public int getID() {
		return id;
	}
	
	public int getTex() {
		return tex;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getCost() {
		return mana_cost;
	}

	public ManaType getType() {
		return mana_type;
	}
	
}
