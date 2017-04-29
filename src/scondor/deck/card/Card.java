package scondor.deck.card;

import scondor.deck.card.fcode.Compilable;
import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.FCode;

// TODO replace Compilable<CompileData> with Compilable<AttackCompileData>
public abstract class Card<CARDDATA extends CardData> implements Compilable<CompileData> {

	protected CARDDATA data;
	protected FCode fcode;
	private int uuid;
	private static int count = 0;

	public Card(CARDDATA data, FCode fcode) {
		this.data = data;
		this.fcode = fcode;
		this.uuid = count++;
	}
	
	public Card(int uuid, CARDDATA data, FCode fcode) {
		this.data = data;
		this.fcode = fcode;
		this.uuid = uuid;
	}
	
	public abstract Card<?> cloneCard();

	public int getUUID() {
		return uuid;
	}
	
	public CARDDATA getData() {
		return data;
	}
	
	public <DATA extends CompileData> void execute(DATA data) {
		fcode.execute(data, this);
	}
	
}
