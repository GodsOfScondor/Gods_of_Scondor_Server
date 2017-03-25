package scondor.deck;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.card.Card;
import scondor.deck.card.CardLoader;
import scondor.god.God;
import scondor.player.PlayerData;

public class Deck {
	
	private DeckData data;
	
	private List<Card<?>> cards = new ArrayList<>();
	private God god;
	
	public Deck(DeckData data, PlayerData player) {
		this.data = data;
		for (int id : data.getCards()) cards.add(CardLoader.getCard(id));
		this.god = new God(data.getGod(), player);
	}

	public DeckData getData() {
		return data;
	}

	public List<Card<?>> getCards() {
		return cards;
	}

	public God getGod() {
		return god;
	}
	
}
