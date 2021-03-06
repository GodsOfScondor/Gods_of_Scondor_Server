package scondor.session;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.card.Card;
import scondor.deck.card.CardData;
import scondor.deck.card.fieldcard.FieldCardData;
import scondor.deck.card.troops.ATCardData;
import scondor.deck.card.troops.DTCardData;
import scondor.mana.ManaType;
import scondor.packets.Message;
import scondor.packets.State;

public class Session {
	
	private int id;
	private PlayerSide p1, p2;
	private GameState state;
	
	private static final ATCardData DUMMY = new ATCardData(-1, "", "", 0, ManaType.NONE, 0, 0, 0);
	
	public Session(int id, PlayerSide p1, PlayerSide p2) {
		this.id = id;
		this.p1 = p1;
		this.p2 = p2;
		this.state = GameState.PLAYER1;
	}
	
	public int getID() {
		return id;
	}
	
	public void switchPlayers() {
		state = state == GameState.PLAYER1 ? GameState.PLAYER2 : GameState.PLAYER1;
		getPlayer().send(new Message("fight;action;turn"));
		getEnemy().send(new Message("fight;action;wait"));
	}
	
	public PlayerSide getPlayer() {
		if (state==GameState.PLAYER1) return p1;
		else return p2;
	}
	
	public PlayerSide getEnemy() {
		if (state==GameState.PLAYER1) return p2;
		else return p1;
	}
	
	public PlayerSide getMaster() {
		return p1;
	}
	
	public PlayerSide getSlave() {
		return p2;
	}
	
	public Session cloneSession() {
		return new Session(id+1, p1, p2);
	}
	
	public State createState(String params, GameState player) {
		return new State(
				generate(player==GameState.PLAYER1 ? getMaster() : getSlave(), false),
				generate(player==GameState.PLAYER2 ? getMaster() : getSlave(), true),
				params);
	}
	
	private PlayerSideData generate(PlayerSide player, boolean hide) {
		
		List<CardData> hand = new ArrayList<>();
		if (!hide) for (Card<?> card : player.getHand()) hand.add(card.getData());
		else for (int n=0;n<player.getHand().size();n++) hand.add(DUMMY);
		
		List<CardData> stack = new ArrayList<>();
		if (!hide) for (Card<?> card : player.getStack()) stack.add(card.getData());
		
		List<CardData> graveyard = new ArrayList<>();
		if (!hide) for (Card<?> card : player.getGraveyard()) graveyard.add(card.getData());
		
		ATCardData[] attackers = new ATCardData[player.getAttackers().length];
		for (int n = 0;n<player.getAttackers().length;n++) {
			if (player.getAttackers()[n] == null) attackers[n] = null;
			else attackers[n]= player.getAttackers()[n].getData();
		}
		
		DTCardData[] defenders = new DTCardData[player.getDefenders().length];
		for (int n = 0;n<player.getDefenders().length;n++) {
			if (player.getDefenders()[n] == null) defenders[n] = null;
			else defenders[n]= player.getDefenders()[n].getData();
		}
		
		FieldCardData fielddata = null;
		if (player.getFieldcard()!=null) player.getFieldcard().getData();
		
		return new PlayerSideData(fielddata, hand, stack, graveyard, attackers, defenders, player.getGoddata(), player.getManaData());
		
	}

	public GameState getState() {
		return state;
	}
	
}
