package scondor.god;

import scondor.player.PlayerData;

public class God {
	
	private GodData data;
	private int level = 0;
	private int exp = 0;
	
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
	
}
