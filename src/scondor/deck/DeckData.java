package scondor.deck;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.card.Card;
import scondor.god.GodData;

public class DeckData {
	
	private List<Card> cards = new ArrayList<>();
	private GodData god;
	private int id;
	
	public DeckData(int id, List<Card> cards, GodData god) {
		this.id=id;
		this.cards = new ArrayList<>(cards);
		this.god = god;
	}
	
	public List<Card> getCards() {
		return cards;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}
	
	public GodData getGod() {
		return god;
	}

}
