package scondor.event.cardevent;

import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.Vars;
import scondor.event.Event;
import scondor.event.EventData;

public class CardEvent extends Event {

	public CardEvent(EventData data) {
		super(data);
	}

	@Override
	public void compile(Vars vars, String cmd, CompileData data) {
		
	}

}
