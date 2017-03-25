package scondor.session;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.card.Card;
import scondor.deck.card.CardData;
import scondor.deck.card.fieldcard.FieldCardData;
import scondor.deck.card.troops.TroopCardData;
import scondor.packets.State;

public class Session {
	
	private int id;
	private PlayerSide p1, p2;
	private GameState state;
	
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
	}
	
	public PlayerSide getPlayer() {
		if (state==GameState.PLAYER1) return p1;
		else return p2;
	}
	
	public PlayerSide getEnemy() {
		if (state==GameState.PLAYER1) return p2;
		else return p1;
	}
	
	public Session cloneSession() {
		return new Session(id+1, p1, p2);
	}
	
	public State createState(String params) {
		return new State(generate(p1), generate(p2), params);
	}
	
	private PlayerSideData generate(PlayerSide player) {
		
		List<CardData> hand = new ArrayList<>();
		for (Card<?> card : player.getHand()) hand.add(card.getData());
		
		List<CardData> stack = new ArrayList<>();
		for (Card<?> card : player.getStack()) stack.add(card.getData());
		
		List<CardData> graveyard = new ArrayList<>();
		for (Card<?> card : player.getGraveyard()) graveyard.add(card.getData());
		
		TroopCardData[] attackers = new TroopCardData[player.getAttackers().length];
		for (int n = 0;n<player.getAttackers().length;n++) {
			if (player.getAttackers()[n] == null) attackers[n] = null;
			else attackers[n]= player.getAttackers()[n].getData();
		}
		
		TroopCardData[] defenders = new TroopCardData[player.getDefenders().length];
		for (int n = 0;n<player.getDefenders().length;n++) {
			if (player.getDefenders()[n] == null) defenders[n] = null;
			else defenders[n]= player.getDefenders()[n].getData();
		}
		
		FieldCardData fielddata = null;
		if (player.getFieldcard()!=null) player.getFieldcard().getData();
		
		return new PlayerSideData(fielddata, hand, stack, graveyard, attackers, defenders, null /*player.getGoddata()*/);
		
	}
	
}
