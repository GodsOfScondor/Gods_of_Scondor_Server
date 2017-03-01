package scondor.deck.card.troops;

import scondor.deck.card.CardData;
import scondor.mana.ManaType;

public class TroopCardData extends CardData {
	
	private static final long serialVersionUID = -1758970014931750800L;
	private int attack, live, countdown;
	
	public TroopCardData(String name, String description, int mana_cost, ManaType mana_type, int attack, int live, int countdown) {
		super(name, description, mana_cost, mana_type);
		this.attack = attack;
		this.live = live;
		this.countdown = countdown;
	}

	public int getAttack() {
		return attack;
	}

	public int getLive() {
		return live;
	}

	public int getCountdown() {
		return countdown;
	}

}
