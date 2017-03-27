package scondor.deck.card.fieldcard;

import scondor.deck.card.Card;
import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.FCode;
import scondor.deck.card.fcode.Vars;

public class FieldCard extends Card<FieldCardData> {

	public FieldCard(FieldCardData data, FCode fcode) {
		super(data, fcode);
	}

	@Override
	public void compile(Vars vars, String cmd, CompileData data) {
		
	}

	@Override
	public Card<?> cloneCard() {
		return new FieldCard((FieldCardData) data.cloneCard(), fcode);
	}
	
}
