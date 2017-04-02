package scondor.deck.card;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.FCode;
import scondor.event.Effect;

public abstract class Card<CARDDATA extends CardData> implements Compilable {

	protected CARDDATA data;
	protected FCode fcode;
	protected List<Effect> effects;

	public Card(CARDDATA data, FCode fcode) {
		this.data = data;
		this.fcode = fcode;
		this.effects = new ArrayList<>();
	}
	
	public void addEffect(Effect effect) {
		this.effects.add(effect);
	}
	
	public List<Effect> getEffects() {
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
