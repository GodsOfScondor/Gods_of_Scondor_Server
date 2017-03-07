package scondor.deck;

import java.util.ArrayList;
import java.util.List;

import scondor.god.GodData;

public class DeckData {
	
	private List<Integer> deck = new ArrayList<>();
	private GodData god;
	
	public DeckData(List<Integer> deck, GodData data) {
		this.deck = deck;
	}
	
	public List<Integer> getDeck() {
		return deck;
	}
	
	public GodData getGod() {
		return god;
	}
}
