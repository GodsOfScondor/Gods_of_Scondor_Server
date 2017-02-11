package scondor.deck.card.troops;

import scondor.deck.card.fcode.FCodeInterpreter;
import scondor.deck.card.fcode.Target;
import scondor.mana.ManaType;

public class AttackTroop extends TroopCardData {

	private int countdown;
	FCodeInterpreter fc;
	
	public AttackTroop(int mana_cost, int attack, int defense, ManaType mana_type, int countdown, int fcode) {
		super(mana_cost, attack, defense, mana_type, fcode);
		this.setCountdown(countdown);
	}

	public int getCountdown() {
		return countdown;
	}

	public void setCountdown(int countdown) {
		this.countdown = countdown;
	}

	@Override
	public void attack(Target t) {
		fc=new FCodeInterpreter(super.getFCode(), t);
	}

}
