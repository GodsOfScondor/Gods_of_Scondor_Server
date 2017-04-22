package scondor.deck.card;

import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.Vars;

public interface Compilable<DATA extends CompileData> {
	
	public void compile(Vars vars, String cmd, DATA data);
	
}
