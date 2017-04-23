package scondor.event.cardevent;

import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.Vars;
import scondor.deck.card.troops.DTCard;
import scondor.event.EventData;

public class DTCardEvent extends CardEvent<DTCard> {

	public DTCardEvent(DTCard victim, EventData data) {
		super(victim, data);
	}

	@Override
	public void compile(Vars vars, String cmd, CompileData data) {
		super.compile(vars, cmd, data);
		
		super.getCard().getData().getDescription(); // unnedig (temporaer)
	}

}
