package scondor.deck.card;

import scondor.deck.card.fcode.FCode;
import scondor.deck.card.fcode.Target;

public interface Compilable {
	
	public void compile(FCode fc,String cmd, Target target);
	
}
