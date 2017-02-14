package scondor.deck.card;

import scondor.deck.card.fcode.FCode;
import scondor.deck.card.fcode.FCodeReader;
import scondor.deck.card.fcode.Target;
import scondor.deck.card.fcode.TargetType;
import scondor.mana.ManaType;

public abstract class Card implements Compilable {

	private int mana_cost;
	private ManaType mana_type;
	private FCode fcip;
	private Target self;

	public Card(int mana_cost, ManaType mana_type, int fcode) {
		this.mana_type = mana_type;
		this.mana_cost = mana_cost;
		fcip = FCodeReader.getFCode(fcode);
		this.self = new Target(this, TargetType.SINGLE_TARGET);
	}

	public int getManaCost() {
		return mana_cost;
	}

	public void setManaCost(int mana_cost) {
		this.mana_cost = mana_cost;
	}

	public ManaType getManatype() {
		return mana_type;
	}

	public void setManatype(ManaType manatype) {
		this.mana_type = manatype;
	}

	public void triggerFCode(Target target) {
		fcip.trigger(self, target);
	}

}
