package scondor.deck.card;

import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.FCode;
import scondor.deck.card.fcode.Vars;

public abstract class Card<CARDDATA extends CardData> implements Compilable {

	private CARDDATA data;
	private FCode fcode;

	public Card(CARDDATA data, FCode fcode) {
		this.data = data;
		this.fcode = fcode;
	}
	
	public Card<?> cloneCard() {
		return new Card<CardData>(data.cloneCard(), fcode) {

			@Override
			public void compile(Vars vars, String cmd, CompileData data) {
				// TODO Auto-generated method stub
				
			}
			
		};
	}

	public CARDDATA getData() {
		return data;
	}
	
	public void trigger(CompileData data) {
		fcode.execute(data, this);
	}

}
