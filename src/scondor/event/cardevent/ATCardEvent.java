package scondor.event.cardevent;

import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.Vars;
import scondor.deck.card.troops.ATCard;
import scondor.event.EventData;

public class ATCardEvent extends CardEvent<ATCard> {

	private ATCard victim;
	
	public ATCardEvent(ATCard victim, EventData data) {
		super(victim, data);
		this.victim = victim;
	}

	@Override
	public void compile(Vars vars, String cmd, CompileData data) {
		super.compile(vars, cmd, data);
		
		victim.getData().getDescription(); // unnedig (temporaer)
	}

}
