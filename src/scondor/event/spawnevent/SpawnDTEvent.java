package scondor.event.spawnevent;

import scondor.deck.card.fcode.Vars;
import scondor.deck.card.troops.DTCard;
import scondor.event.EventData;

public class SpawnDTEvent extends SpawnTroopEvent<SpawnCompileData<DTCard>> {

	public SpawnDTEvent(EventData data) {
		super(data);
	}

	@Override
	public void compile(Vars vars, String cmd, SpawnCompileData<DTCard> data) {
		
	}
	
}
