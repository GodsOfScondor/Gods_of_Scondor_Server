package scondor.session;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.DeckData;
import scondor.deck.card.Card;
import scondor.deck.card.fieldcard.FieldCard;
import scondor.deck.card.troops.AttackTroop;
import scondor.deck.card.troops.DefenseTroop;
import scondor.god.GodData;

public class GamePlayer {
	
	private DeckData deck;
	private List<Card> graveyard;
	private List<Card> stack;
	private List<Card> hand;
	private FieldCard fieldcard;
	private AttackTroop[] attackers;
	private DefenseTroop[] defenders;
	private GodData goddata;
	
	public GamePlayer(DeckData deck) {
		this.deck = deck;
		this.graveyard = new ArrayList<>();
		this.hand = new ArrayList<>();
		this.fieldcard = null;
		this.attackers = new AttackTroop[5];
		this.defenders = new DefenseTroop[5];
		this.goddata = new GodData(deck.getGod());
	}

	/**
	 * raw data only!
	 */
	public DeckData getDeck() {
		return deck;
	}

	public void setDeck(DeckData deck) {
		this.deck = deck;
	}

	public List<Card> getGraveyard() {
		return graveyard;
	}

	public void setGraveyard(List<Card> graveyard) {
		this.graveyard = graveyard;
	}

	public List<Card> getStack() {
		return stack;
	}

	public void setStack(List<Card> stack) {
		this.stack = stack;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public FieldCard getFieldcard() {
		return fieldcard;
	}

	public void setFieldcard(FieldCard fieldcard) {
		this.fieldcard = fieldcard;
	}

	public AttackTroop[] getAttackers() {
		return attackers;
	}

	public void setAttackers(AttackTroop[] attackers) {
		this.attackers = attackers;
	}

	public DefenseTroop[] getDefenders() {
		return defenders;
	}

	public void setDefenders(DefenseTroop[] defenders) {
		this.defenders = defenders;
	}

	public GodData getGoddata() {
		return goddata;
	}

	public void setGoddata(GodData goddata) {
		this.goddata = goddata;
	}
	
}
