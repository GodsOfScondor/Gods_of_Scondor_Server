package scondor.event;

import scondor.deck.card.fcode.Compilable;
import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.FCode;
import scondor.deck.card.fcode.FCodeLoader;

public abstract class Event<COMPILER extends CompileData> implements Compilable<COMPILER> {
	
	private FCode fcode;
	private EventData data;
	private boolean remove;
	
	public Event(EventData data) {
		this.data = data;
		this.fcode = FCodeLoader.getFCode(data.getFCode());
		this.remove = false;
	}
	
	public boolean trigger(COMPILER data) {
		fcode.execute(data, this);
		return remove;
	}
	
	public EventData getData() {
		return data;
	}
	
}
