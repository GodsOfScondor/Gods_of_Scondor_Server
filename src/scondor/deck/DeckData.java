package scondor.deck;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.card.Card;

public class DeckData {
	
	private List<Card> cards = new ArrayList<>();
	private int id;
	
	public DeckData(int id) {
		this.id=id;
	}
	
	public List<Card> getCards() {
		return cards;
	}
	
	public void addCard(Card card){
		cards.add(card);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
