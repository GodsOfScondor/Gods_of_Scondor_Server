package scondor.deck.card;

import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.Vars;

public interface Compilable {
	
	public void compile(Vars vars, String cmd, CompileData data);
	
}
