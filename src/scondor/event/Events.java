package scondor.event;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.card.fcode.CompileData;

public class Events<COMPILER extends CompileData, EVENT extends Event<COMPILER>> {
	
	private List<EVENT> events;
	private List<EVENT> remove;
	
	public Events() {
		this.events = new ArrayList<>();
	}
	
	public void addEvent(EVENT event) {
		events.add(event);
	}
	
	public void trigger(COMPILER data) {
		for (EVENT effect : events) {
			if (effect.trigger(data)) remove.add(effect);
		}
		for (EVENT effect : remove) events.remove(effect);
	}

	public void triggerEvent(EventFilter<COMPILER, EVENT> filter, COMPILER data) {
		
		EVENT event = filter.filter(events);
		
		if (event.trigger(data)) events.remove(event);
		
		
	}
	
}
