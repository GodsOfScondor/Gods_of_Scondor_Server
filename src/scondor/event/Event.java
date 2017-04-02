package scondor.event;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.card.fcode.CompileData;

public class Event<EFFECT extends Effect> {
	
	private List<EFFECT> events;
	private List<EFFECT> remove;
	
	public Event() {
		this.events = new ArrayList<>();
	}
	
	public void addEvent(EFFECT event) {
		events.add(event);
	}
	
	public void trigger(CompileData data) {
		for (EFFECT effect : events) {
			if (effect.trigger(data)) remove.add(effect);
		}
		for (EFFECT effect : remove) events.remove(effect);
	}
	
}
