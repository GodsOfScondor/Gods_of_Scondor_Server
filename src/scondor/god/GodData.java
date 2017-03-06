package scondor.god;

import java.io.Serializable;

import scondor.mana.ManaType;

public class GodData implements Serializable {
	
	private static final long serialVersionUID = 3231458288499536154L;
	private ManaType manatype1;
	private ManaType manatype2;
	private int level;
	private int exp;
	private int manatype1_amount;
	private int manatype2_amount;
	private int universal_mana_amount;

	public GodData(int level, int exp, ManaType manatype1, ManaType manatype2){
		this.level=level;
		this.exp=exp;
		this.manatype1=manatype1;
		this.manatype2=manatype2;
	}
	
	public GodData cloneGod() {
		try { return (GodData) this.clone(); }
		catch (CloneNotSupportedException e) { e.printStackTrace(); }
		return null;
	}
	
	public ManaType getManatype1() {
		return manatype1;
	}

	public void setManatype1(ManaType manatype1) {
		this.manatype1 = manatype1;
	}

	public ManaType getManatype2() {
		return manatype2;
	}

	public void setManatype2(ManaType manatype2) {
		this.manatype2 = manatype2;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getEXP() {
		return exp;
	}

	public void setEXP(int exp) {
		this.exp = exp;
	}

	public int getMana1() {
		return manatype1_amount;
	}

	public void setMana1(int manatype1_amount) {
		this.manatype1_amount = manatype1_amount;
	}

	public int getMana2() {
		return manatype2_amount;
	}

	public void setMana2(int manatype2_amount) {
		this.manatype2_amount = manatype2_amount;
	}

	public int getUniversalMana() {
		return universal_mana_amount;
	}

	public void setUniversalMana(int universal_mana_amount) {
		this.universal_mana_amount = universal_mana_amount;
	}
	
}
