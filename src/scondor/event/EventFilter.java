package scondor.event;

import java.util.List;

import scondor.deck.card.fcode.CompileData;

public interface EventFilter<COMPILER extends CompileData, EVENT extends Event<COMPILER>> {
	
	public EVENT filter(List<EVENT> events);
	
}
