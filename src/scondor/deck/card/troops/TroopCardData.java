package scondor.deck.card.troops;

import scondor.deck.card.Card;
import scondor.mana.ManaType;

public abstract class TroopCardData extends Card {

	private int attack;
	private int live;

	public TroopCardData(int mana_cost, int attack, int live, ManaType mana_type, int fcode) {
		super(mana_cost, mana_type,fcode);
		this.attack = attack;
		this.live = live;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getLive() {
		return live;
	}

	public void setLive(int defense) {
		this.live = defense;
	}
	
}