package scondor.god;

import scondor.player.PlayerData;

public class God {
	
	private GodData data;
	private int level = 0;
	private int exp = 0;
	private int mana1 = 0;
	private int mana2 = 0;
	private int universal = 0;
	
	public God(GodData data, PlayerData player) {
		this.data = data;
		// TODO get values(level, exp) from DB
	}
	
	public GodData getData() {
		return data;
	}

	public int getExp() {
		return exp;
	}

	public void setEXP(int exp) {
		this.level = exp;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMana1() {
		return mana1;
	}

	public void setMana1(int mana1) {
		this.mana1 = mana1;
	}

	public int getMana2() {
		return mana2;
	}

	public void setMana2(int mana2) {
		this.mana2 = mana2;
	}

	public int getUniversalMana() {
		return universal;
	}

	public void setUniversalMana(int universal) {
		this.universal = universal;
	}
	
}
