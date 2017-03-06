package scondor.session;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.DeckData;
import scondor.deck.card.Card;
import scondor.deck.card.fieldcard.FieldCard;
import scondor.deck.card.troops.AttackTroopCard;
import scondor.deck.card.troops.DefenseTroopCard;
import scondor.god.GodData;
import scondor.player.Player;

public class GamePlayer {
	
	private Player player;
	private DeckData deck;
	
	private List<Card<?>> graveyard;
	private List<Card<?>> stack;
	private List<Card<?>> hand;
	private FieldCard fieldcard;
	private AttackTroopCard[] attackers;
	private DefenseTroopCard[] defenders;
	private GodData goddata;
	
	public GamePlayer(Player player, DeckData deck) {
		this.player = player;
		this.deck = deck;
		this.graveyard = new ArrayList<>();
		this.hand = new ArrayList<>();
		this.fieldcard = null;
		this.attackers = new AttackTroopCard[5];
		this.defenders = new DefenseTroopCard[5];
		this.goddata = deck.getGod().cloneGod();
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

	public List<Card<?>> getGraveyard() {
		return graveyard;
	}

	public void setGraveyard(List<Card<?>> graveyard) {
		this.graveyard = graveyard;
	}

	public List<Card<?>> getStack() {
		return stack;
	}

	public void setStack(List<Card<?>> stack) {
		this.stack = stack;
	}

	public List<Card<?>> getHand() {
		return hand;
	}

	public void setHand(List<Card<?>> hand) {
		this.hand = hand;
	}

	public FieldCard getFieldcard() {
		return fieldcard;
	}

	public void setFieldcard(FieldCard fieldcard) {
		this.fieldcard = fieldcard;
	}

	public AttackTroopCard[] getAttackers() {
		return attackers;
	}

	public void setAttackers(AttackTroopCard[] attackers) {
		this.attackers = attackers;
	}

	public DefenseTroopCard[] getDefenders() {
		return defenders;
	}

	public void setDefenders(DefenseTroopCard[] defenders) {
		this.defenders = defenders;
	}

	public GodData getGoddata() {
		return goddata;
	}

	public void setGoddata(GodData goddata) {
		this.goddata = goddata;
	}
	
	public Player getPlayer() {
		return player;
	}
	
}
