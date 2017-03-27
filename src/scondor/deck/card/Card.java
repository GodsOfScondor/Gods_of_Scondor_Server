package scondor.deck.card;

import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.FCode;

public abstract class Card<CARDDATA extends CardData> implements Compilable {

	protected CARDDATA data;
	protected FCode fcode;

	public Card(CARDDATA data, FCode fcode) {
		this.data = data;
		this.fcode = fcode;
	}
	
	public abstract Card<?> cloneCard();

	public CARDDATA getData() {
		return data;
	}
	
	public void trigger(CompileData data) {
		fcode.execute(data, this);
	}

}
