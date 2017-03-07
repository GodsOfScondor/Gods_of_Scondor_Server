package scondor.session;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.Deck;
import scondor.deck.card.Card;
import scondor.deck.card.fieldcard.FieldCard;
import scondor.deck.card.troops.ATCard;
import scondor.deck.card.troops.DTCard;
import scondor.god.GodData;
import scondor.player.Player;

/**
 * 
 * @author Bernhard Scharrer
 * 
 * represents all references to data and there functionalities from one side of a game session.
 *
 */
public class PlayerSide {
	
	private Player player;
	private Deck deck;
	
	private List<Card<?>> graveyard;
	private List<Card<?>> stack;
	private List<Card<?>> hand;
	private FieldCard fieldcard;
	private ATCard[] attackers;
	private DTCard[] defenders;
	private GodData goddata;
	
	public PlayerSide(Player player, Deck deck) {
		this.player = player;
		this.deck = deck;
		this.graveyard = new ArrayList<>();
		this.hand = new ArrayList<>();
		this.fieldcard = null;
		this.attackers = new ATCard[5];
		this.defenders = new DTCard[5];
		this.goddata = deck.getGod().getData();
	}

	/**
	 * raw data only!
	 */
	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
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

	public ATCard[] getAttackers() {
		return attackers;
	}

	public void setAttackers(ATCard[] attackers) {
		this.attackers = attackers;
	}

	public DTCard[] getDefenders() {
		return defenders;
	}

	public void setDefenders(DTCard[] defenders) {
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
