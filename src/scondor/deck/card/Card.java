package scondor.deck.card;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.FCode;
import scondor.event.Event;

public abstract class Card<CARDDATA extends CardData> implements Compilable {

	protected CARDDATA data;
	protected FCode fcode;
	protected List<Event> effects;

	public Card(CARDDATA data, FCode fcode) {
		this.data = data;
		this.fcode = fcode;
		this.effects = new ArrayList<>();
	}
	
	public void addEffect(Event effect) {
		this.effects.add(effect);
	}
	
	public List<Event> getEffects() {
		return effects;
	}
	
	public abstract Card<?> cloneCard();

	public CARDDATA getData() {
		return data;
	}
	
	public void trigger(CompileData data) {
		fcode.execute(data, this);
	}

}
