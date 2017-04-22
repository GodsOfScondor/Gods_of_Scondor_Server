package scondor.event.spawnevent;

import scondor.deck.card.fcode.Vars;
import scondor.deck.card.troops.ATCard;
import scondor.event.EventData;

public class SpawnATEvent extends SpawnTroopEvent<SpawnCompileData<ATCard>> {

	public SpawnATEvent(EventData data) {
		super(data);
	}

	@Override
	public void compile(Vars vars, String cmd, SpawnCompileData<ATCard> data) {
		
		
		
	}
	
}
