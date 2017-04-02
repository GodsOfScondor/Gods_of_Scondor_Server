package scondor.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import scondor.deck.Deck;
import scondor.deck.card.Card;
import scondor.deck.card.fieldcard.FieldCard;
import scondor.deck.card.troops.ATCard;
import scondor.deck.card.troops.DTCard;
import scondor.deck.card.troops.TroopCardData;
import scondor.gnet.packet.Packet;
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
	
	private static final int START_AMOUNT = 7;
	private static final int MAX_ROWS = 4;
	
	private Player player;
	private Deck deck;
	
	private List<Card<?>> graveyard;
	private List<Card<?>> stack;
	private List<Card<?>> hand;
	private FieldCard fieldcard;
	private ATCard[] attackers;
	private TroopCardData[] attackers_data;
	private DTCard[] defenders;
	private TroopCardData[] defenders_data;
	private GodData goddata;
	
	public PlayerSide(Player player, Deck deck) {
		this.player = player;
		this.deck = deck;
		this.stack = new ArrayList<>();
		this.graveyard = new ArrayList<>();
		this.hand = new ArrayList<>();
		
		for (Card<?> card : deck.getCards()) {
			stack.add(card.cloneCard());
		}
		
		pickFromStack(START_AMOUNT);
		
		this.fieldcard = null;
		this.attackers = new ATCard[MAX_ROWS];
		this.defenders = new DTCard[MAX_ROWS];
		this.attackers_data = new TroopCardData[MAX_ROWS];
		this.defenders_data = new TroopCardData[MAX_ROWS];
		this.goddata = deck.getGod().getData();
	}
	
	public void pickFromStack(int amount) {
		for (int n = 0;n < amount;n++) {
			Card<?> card = getRandom(stack);
			stack.remove(card);
			hand.add(card);
		}
	}
	
	private Card<?> getRandom(List<Card<?>> list) {
		Card<?> card = list.get(new Random().nextInt(list.size()));
		return card;
	}
	
	public void send(Packet packet) {
		player.getClient().sendPacket(packet);
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
	
	public void playATOut(Card<?> card, int slot) {
		
		if (hand.contains(card)) {
			if (card instanceof ATCard) {
				if (attackers[slot]==null) {
					attackers[slot] = (ATCard) card.cloneCard();
					attackers_data[slot] = (TroopCardData) attackers[slot].getData().cloneCard();
				}
			}
		}
		
	}
	
	public void playDTOut(Card<?> card, int slot) {
		
		if (hand.contains(card)) {
			if (card instanceof ATCard) {
				if (attackers[slot]==null) {
					attackers[slot] = (ATCard) card.cloneCard();
					attackers_data[slot] = (TroopCardData) attackers[slot].getData().cloneCard();
				}
			}
		}
		
	}
	
	public void attack() {
		
		for (int n = 0;n<MAX_ROWS;n++) {
			if (attackers[n]!=null) {
				if (attackers[n].getData().getCountdown()==0) {
					
					
					
				}
			}
		}
		
	}
	
}
