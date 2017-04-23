package scondor.event.countdown;

import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.TargetType;
import scondor.deck.card.troops.ATCard;
import scondor.session.PlayerSide;

public class CountdownCompileData extends CompileData {

	private ATCard card;
	
	public CountdownCompileData(PlayerSide player, PlayerSide enemy, ATCard card) {
		super(player, enemy, TargetType.NONE);
		this.card = card;
	}
	
	public ATCard getCard() {
		return card;
	}

}
