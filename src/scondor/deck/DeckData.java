package scondor.deck;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.card.CardData;

public class DeckData {
	
	private List<CardData> cards = new ArrayList<>();
	
	public DeckData(List<CardData> cards) {
		this.cards = cards;
	}
	
	public List<CardData> getCards() {
		return cards;
	}

}
