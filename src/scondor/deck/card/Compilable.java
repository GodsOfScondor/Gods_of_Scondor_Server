package scondor.deck.card;

import scondor.deck.card.fcode.Target;

public interface Compilable {
	
	public void compile(String cmd, Target target);
	
}
