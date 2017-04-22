package scondor.event.cardevent;

import scondor.deck.card.Card;
import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.Vars;
import scondor.event.Event;
import scondor.event.EventData;

public class CardEvent extends Event<CompileData> {

	private Card<?> victim;
	
	public CardEvent(Card<?> victim, EventData data) {
		super(data);
		this.victim = victim;
	}

	@Override
	public void compile(Vars vars, String cmd, CompileData data) {
		victim.getData().getDescription(); // unnedig (temporaer)
	}

}
