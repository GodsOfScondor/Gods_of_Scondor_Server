package scondor.event.spawnevent;

import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.Vars;
import scondor.event.Event;
import scondor.event.EventData;

public class SpawnTroopEvent<DATA extends CompileData> extends Event<DATA> {

	public SpawnTroopEvent(EventData data) {
		super(data);
	}

	@Override
	public void compile(Vars vars, String cmd, CompileData data) {
		
	}
	
}
