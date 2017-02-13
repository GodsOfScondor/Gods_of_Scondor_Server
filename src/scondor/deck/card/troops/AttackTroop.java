package scondor.deck.card.troops;

import scondor.deck.card.fcode.FCodeInterpreter;
import scondor.deck.card.fcode.Target;
import scondor.mana.ManaType;

public class AttackTroop extends TroopCardData {

	private int countdown;
	private FCodeInterpreter fc;

	public AttackTroop(int mana_cost, int attack, int defense, ManaType mana_type, int countdown, int fcode) {
		super(mana_cost, attack, defense, mana_type, fcode);
		this.setCountdown(countdown);
		fc = new FCodeInterpreter(fcode);
	}

	public int getCountdown() {
		return countdown;
	}

	public void setCountdown(int countdown) {
		this.countdown = countdown;
	}

	@Override
	public void compile(String cmd, Target target) {
		/*
		 * get attack of enemy
		 */
		if (cmd.startsWith("getEnemyAttack")) {
			fc.getVar((cmd.split("(")[1].split(")")[0])).setValue(((TroopCardData) target.getCard()).getAttack());
		}
		/*
		 * set attack of enemy
		 */
		else if (cmd.startsWith("setEnemyAttack")) {
			((TroopCardData) target.getCard()).setAttack(fc.getVar(cmd.split("(")[1].split(")")[0]).getValue());
		}
		/*
		 * set attack of enemy using offset value
		 */
		else if (cmd.startsWith("editEnemyAttack")) {
			((TroopCardData) target.getCard()).setAttack(((TroopCardData) target.getCard()).getAttack()
					+ (fc.getVar(cmd.split("(")[1].split(")")[0]).getValue()));
		}
	}

}
