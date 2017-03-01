package scondor.deck.card;

import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.FCode;

public abstract class Card<TYPE extends CardData> implements Compilable {

	private TYPE data;
	private FCode fcode;

	public Card(TYPE data, FCode fcode) {
		this.data = data;
		this.fcode = fcode;
	}
	
	public Card(Card<? extends CardData> card) {
		
	}
	
	@SuppressWarnings("unchecked")
	public Card<TYPE> cloneCard() {
		try { return (Card<TYPE>) this.clone(); } 
		catch (CloneNotSupportedException e) { e.printStackTrace(); }
		return null;
	}

	public TYPE getData() {
		return data;
	}
	
	public void trigger(CompileData data) {
		fcode.execute(data, this);
	}

}
