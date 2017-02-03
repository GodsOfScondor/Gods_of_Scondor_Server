package scondor.player;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.DeckData;
import scondor.deck.card.CardData;

public class Player {
	private DeckData player_deck;
	private List<CardData> card_list=new ArrayList<>();
	
	public Player(){
		this.setPlayer_deck(new DeckData(card_list));
	}

	public DeckData getPlayer_deck() {
		return player_deck;
	}

	public void setPlayer_deck(DeckData player_deck) {
		this.player_deck = player_deck;
	}
	
}
