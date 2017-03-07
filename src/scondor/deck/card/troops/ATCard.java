package scondor.deck.card.troops;

import scondor.deck.card.Card;
import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.FCode;
import scondor.deck.card.fcode.Vars;

public class ATCard extends Card<TroopCardData> {

	public ATCard(TroopCardData data, FCode fcode) {
		super(data, fcode);
	}
	
	@Override
	public void compile(Vars vars, String cmd, CompileData data) {
		
//		
//		
//		/*
//		 * get attack of enemy
//		 */
//		if (cmd.startsWith("getEnemyAttack")) {
//			fc.getVar(cmd.split("\"")[1]).setValue(((TroopCardDat<>) target.getCard()).getAttack());
//		}
//		/*
//		 * set attack of enemy
//		 */
//		else if (cmd.startsWith("setEnemyAttack")) {
//			((TroopCard) target.getCard()).setAttack(Integer.parseInt(cmd.split("\"")[1]));
//		}
//		/*
//		 * set attack of enemy using offset value
//		 */
//		else if (cmd.startsWith("editEnemyAttack")) {
//			((TroopCard) target.getCard()).setAttack(((TroopCard) target.getCard()).getAttack()
//					+ Integer.parseInt(cmd.split("\"")[1]));
//		}
//		
//		
		
	}

}
