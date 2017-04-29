package scondor.deck.card.troops;

import scondor.deck.card.Card;
import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.FCode;
import scondor.deck.card.fcode.Vars;

public class ATCard extends Card<ATCardData> {

	public ATCard(ATCardData data, FCode fcode) {
		super(data, fcode);
	}
	
	public ATCard(int uuid, ATCardData data, FCode fcode) {
		super(uuid, data, fcode);
	}
	
	@Override
	public void compile(Vars vars, String cmd, CompileData data) {
		
		
		
	}

	@Override
	public Card<?> cloneCard() {
		return new ATCard(this.getUUID(), (ATCardData) data.cloneCard(), fcode);
	}

}
