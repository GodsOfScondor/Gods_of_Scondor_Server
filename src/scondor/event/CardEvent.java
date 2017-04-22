package scondor.event;

import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.Vars;

public class CardEvent extends Event {

	public CardEvent(EventData data) {
		super(data);
	}

	@Override
	public void compile(Vars vars, String cmd, CompileData data) {
		
	}

}
