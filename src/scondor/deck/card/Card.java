package scondor.deck.card;

import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.FCode;

public abstract class Card<CARDDATA extends CardData> implements Compilable {

	private CARDDATA data;
	private FCode fcode;

	public Card(CARDDATA data, FCode fcode) {
		this.data = data;
		this.fcode = fcode;
	}
	
	public Card(Card<? extends CardData> card) {
		
	}
	
	@SuppressWarnings("unchecked")
	public Card<CARDDATA> cloneCard() {
		try { return (Card<CARDDATA>) this.clone(); } 
		catch (CloneNotSupportedException e) { e.printStackTrace(); }
		return null;
	}

	public CARDDATA getData() {
		return data;
	}
	
	public void trigger(CompileData data) {
		fcode.execute(data, this);
	}

}
