package scondor.deck;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.card.Card;
import scondor.god.GodData;

public class DeckData {
	
	private List<Card<?>> cards = new ArrayList<>();
	private GodData god;
	private int id;
	
	public DeckData(int id, GodData god) {
		this.id=id;
		this.cards = new ArrayList<>();
		this.god = god;
	}
	
	public List<Card<?>> getCards() {
		return cards;
	}
	
	public void addCard(Card<?> c){
		cards.add(c);
		DeckLoader.reload(this);
	}
	
	public void removeCard(Card<?> c){
		cards.remove(c);
		DeckLoader.reload(this);
	}

	public int getID() {
		return id;
	}
	
	public GodData getGod() {
		return god;
	}

}
