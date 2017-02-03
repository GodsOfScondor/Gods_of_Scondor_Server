package scondor.deck;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.card.Card;

public class DeckData {
	
	private List<Card> cards = new ArrayList<>();
	
	public DeckData(List<Card> cards) {
		this.cards = cards;
	}
	
	public List<Card> getCards() {
		return cards;
	}

}
