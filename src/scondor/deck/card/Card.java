package scondor.deck.card;

import scondor.deck.card.fcode.Compilable;
import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.FCode;

// TODO replace Compilable<CompileData> with Compilable<AttackCompileData>
public abstract class Card<CARDDATA extends CardData> implements Compilable<CompileData> {

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
	
	public <DATA extends CompileData> void execute(DATA data) {
		fcode.execute(data, this);
	}
	
}
