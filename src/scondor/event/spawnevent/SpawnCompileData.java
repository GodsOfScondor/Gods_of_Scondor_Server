package scondor.event.spawnevent;

import scondor.deck.card.Card;
import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.TargetType;
import scondor.session.SessionController;

public class SpawnCompileData<CARD extends Card<?>> extends CompileData {

	private CARD card;
	
	public SpawnCompileData(SessionController controller, CARD card) {
		super(controller, TargetType.NONE);
		this.card = card;
	}

	public CARD getCard() {
		return card;
	}
	
}
