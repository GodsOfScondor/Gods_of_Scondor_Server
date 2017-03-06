package scondor.session;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.card.Card;
import scondor.deck.card.CardData;
import scondor.deck.card.troops.TroopCardData;
import scondor.packets.State;

public class SessionState {
	
	private GamePlayer p1, p2;
	private GameState state;
	
	public SessionState(GamePlayer p1, GamePlayer p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.state = GameState.PLAYER1;
	}
	
	public GamePlayer getPlayer() {
		if (state==GameState.PLAYER1) return p1;
		else return p2;
	}
	
	public GamePlayer getEnemy() {
		if (state==GameState.PLAYER1) return p2;
		else return p1;
	}
	
	public State createState(String params) {
		return new State(generate(p1), generate(p2), params);
	}
	
	private StateData generate(GamePlayer player) {
		
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
		
		return new StateData(player.getFieldcard().getData(), hand, stack, graveyard, attackers, defenders, player.getGoddata());
		
	}
	
}
