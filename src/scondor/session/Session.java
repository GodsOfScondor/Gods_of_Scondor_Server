package scondor.session;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.card.Card;
import scondor.deck.card.CardData;
import scondor.deck.card.troops.TroopCardData;
import scondor.packets.State;

public class Session {
	
	private PlayerSide p1, p2;
	private GameState state;
	
	public Session(PlayerSide p1, PlayerSide p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.state = GameState.PLAYER1;
	}
	
	public PlayerSide getPlayer() {
		if (state==GameState.PLAYER1) return p1;
		else return p2;
	}
	
	public PlayerSide getEnemy() {
		if (state==GameState.PLAYER1) return p2;
		else return p1;
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
		for (int n = 0;n<player.getAttackers().length;n++) attackers[n]= player.getAttackers()[n].getData();
		
		TroopCardData[] defenders = new TroopCardData[player.getDefenders().length];
		for (int n = 0;n<player.getDefenders().length;n++) defenders[n]= player.getDefenders()[n].getData();
		
		return new PlayerSideData(player.getFieldcard().getData(), hand, stack, graveyard, attackers, defenders, player.getGoddata());
		
	}
	
}
