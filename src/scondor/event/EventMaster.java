package scondor.event;

import scondor.deck.card.Card;
import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.TargetType;
import scondor.session.GameState;
import scondor.session.SessionController;

public class EventMaster {
	
	public static void triggerCardEffects(SessionController controller, GameState state) {
		
		if (state==GameState.PLAYER1) for (Card<?> card : controller.getSession().getPlayer().getDeck().getCards()) {
			for (Effect effect : card.getEffects()) effect.trigger(
					new CompileData(
					controller.getSession().getPlayer(),
					controller.getSession().getEnemy(),
					-1,
					TargetType.CARDEFFECT));
		}
		
		if (state==GameState.PLAYER2) for (Card<?> card : controller.getSession().getEnemy().getDeck().getCards()) {
			for (Effect effect : card.getEffects()) effect.trigger(
					new CompileData(
					controller.getSession().getEnemy(),
					controller.getSession().getPlayer(),
					-1,
					TargetType.CARDEFFECT));
		}
		
	}
	
}
