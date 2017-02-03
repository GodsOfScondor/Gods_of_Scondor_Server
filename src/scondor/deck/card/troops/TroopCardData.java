package scondor.deck.card.troops;

import scondor.deck.card.CardData;

public abstract class TroopCardData extends CardData {

	private int attack;
	private int live;
	private int countdown;
	private int fcode;

	public TroopCardData(int mana_cost, int attack, int live, int countdown, int fcode) {
		super(mana_cost);
		this.attack = attack;
		this.live = live;
		this.countdown = countdown;
		this.fcode = fcode;
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

	public int getCountdown() {
		return countdown;
	}

	public void setCountdown(int count) {
		this.countdown = count;
	}
	
	public int getFCode() {
		return fcode;
	}
	
}