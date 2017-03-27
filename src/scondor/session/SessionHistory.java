package scondor.session;

import java.util.ArrayList;
import java.util.List;

import scondor.packets.State;
import scondor.player.Player;

public class SessionHistory {
	
	private static int count = 0;
	private int id;
	private int updates;
	private List<Session> old_states = new ArrayList<>();
	private Session current;
	private GameType type;
	
	public SessionHistory(Session start, GameType type) {
		this.current = start.cloneSession();
		this.old_states.add(start);
		this.id = count++;
		this.type = type;
	}
	
	public int getID() {
		return id;
	}
	
	public void update() {
		
		State state1 = current.createState("update;"+updates, GameState.PLAYER1);
		State state2 = current.createState("update;"+updates, GameState.PLAYER2);
		
		current.getMaster().send(state1);
		current.getSlave().send(state2);
		
		current.switchPlayers();
		old_states.add(current.cloneSession());
		updates++;
	}
	
	public Session getSession() {
		return current;
	}
	
	public Player getPlayer() {
		return current.getPlayer().getPlayer();
	}
	
	public Player getEnemy() {
		return current.getEnemy().getPlayer();
	}
	
	public GameType getGameType() {
		return type;
	}
	
	public List<Session> getOldStates() {
		return old_states;
	}
	
}
