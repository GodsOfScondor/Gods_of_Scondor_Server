package scondor.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import scondor.Console;
import scondor.deck.Deck;
import scondor.deck.card.Card;
import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.TargetType;
import scondor.deck.card.fieldcard.FieldCard;
import scondor.deck.card.troops.ATCard;
import scondor.deck.card.troops.ATCardData;
import scondor.deck.card.troops.DTCard;
import scondor.deck.card.troops.DTCardData;
import scondor.event.EventHandler;
import scondor.gnet.packet.Packet;
import scondor.god.GodData;
import scondor.mana.ManaData;
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
	private ATCardData[] attackers_data;
	private DTCard[] defenders;
	private DTCardData[] defenders_data;
	private GodData goddata;
	private ManaData manadata;
	
	private EventHandler handler;
	
	public PlayerSide(Player player, Deck deck, EventHandler handler) {
		this.player = player;
		this.deck = deck;
		this.stack = new ArrayList<>();
		this.graveyard = new ArrayList<>();
		this.hand = new ArrayList<>();
		this.handler = handler;
		
		for (Card<?> card : deck.getCards()) {
			stack.add(card.cloneCard());
		}
		
		pickFromStack(START_AMOUNT);
		
		this.fieldcard = null;
		this.attackers = new ATCard[MAX_ROWS];
		this.defenders = new DTCard[MAX_ROWS];
		this.attackers_data = new ATCardData[MAX_ROWS];
		this.defenders_data = new DTCardData[MAX_ROWS];
		this.goddata = deck.getGod().getData();
		this.manadata = new ManaData();
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
	
	public ManaData getManaData() {
		return manadata;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	/*
	 * 
	 */
	public void playATOut(ATCard card, int slot) {
		
		if (hand.contains(card)) {
			if (attackers[slot] == null) {
				attackers[slot] = (ATCard) card.cloneCard();
				attackers_data[slot] = (ATCardData) attackers[slot].getData().cloneCard();
				attackers[slot].execute(new CompileData(null, TargetType.NONE));
				handler.triggerSpawnATEvents(SessionMaster.getSession(player.getData().getUsername()), attackers[slot]);
			}
		}
		
	}
	
	/*
	 * 
	 */
	public void playDTOut(DTCard card, int slot) {
		
		if (hand.contains(card)) {
			System.out.println("player has this card in his hand!");
			if (defenders[slot]==null) {
				defenders[slot] = (DTCard) card.cloneCard();
				defenders_data[slot] = (DTCardData) defenders[slot].getData().cloneCard();
				defenders[slot].execute(new CompileData(null, TargetType.NONE));
				handler.triggerSpawnDTEvents(SessionMaster.getSession(player.getData().getUsername()), defenders[slot]);
			}
		}
		
	}
	
	/*
	 * deacreases countdown
	 */
	public void countdown() {
		for (int n = 0;n<MAX_ROWS;n++) {
			if (attackers[n]!=null) {
				if (attackers[n].getData().getCountdown()>0) {
					attackers[n].getData().setCountdown(attackers[n].getData().getCountdown()-1);
				} else {
					Console.error("Troop has not attacked correctly! (" + getPlayer().getData().getUsername() + ">" + n + ": " + attackers[n].getData().getName() + ")");
				}
			}
		}
	}
	
	/*
	 * 
	 */
	public void attack(PlayerSide enemy) {
		
		for (int n = 0;n<MAX_ROWS;n++) {
			if (attackers[n]!=null) {
				if (attackers[n].getData().getCountdown()==0) {
					
					/*
					 * attack defender
					 */
					if (enemy.getDefenders()[n]!=null) {
						/*
						 * trigger troop attack events
						 */
//						EventMaster.triggerDTAttack();
					}
					/*
					 * attack attacker
					 */
					else if (enemy.getAttackers()[n]!=null) {
						/*
						 * trigger troop attack events
						 */
//						EventMaster.triggerATAttack();
					}
					/*
					 * attack god
					 */
					else {
						/*
						 * trigger troop attack events
						 */
//						EventMaster.triggerGodAttack();
					}
					
					
					
					attackers[n].getData().setCountdown(attackers_data[n].getCountdown());
				}
			}
		}
		
	}
	
}
