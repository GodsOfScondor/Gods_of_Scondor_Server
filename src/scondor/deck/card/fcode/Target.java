package scondor.deck.card.fcode;

import scondor.deck.card.Card;

public class Target {
	
	private Card card;
	private TargetType targettype;
	
	
	public Target(Card singlecard, TargetType targettype){
		this.card=singlecard;
		this.targettype=targettype;
	}


	public TargetType getTargettype() {
		return targettype;
	}


	public void setTargettype(TargetType targettype) {
		this.targettype = targettype;
	}


	public Card getCard() {
		return card;
	}


	public void setCard(Card card) {
		this.card = card;
	}
	
}
