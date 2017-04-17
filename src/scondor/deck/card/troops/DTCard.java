package scondor.deck.card.troops;

import scondor.deck.card.Card;
import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.FCode;
import scondor.deck.card.fcode.Vars;

public class DTCard extends Card<DTCardData> {
	
	public DTCard(DTCardData data, FCode fcode) {
		super(data, fcode);
	}

	@Override
	public void compile(Vars vars, String cmd, CompileData data) {
		
		
		
	}

	@Override
	public Card<?> cloneCard() {
		return new DTCard((DTCardData) data.cloneCard(), fcode);
	}

}
