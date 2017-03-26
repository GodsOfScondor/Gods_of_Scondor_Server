package scondor.session;

import java.util.ArrayList;
import java.util.List;

import scondor.player.Player;

public class SessionHistory {
	
	private static int count = 0;
	private int id;
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
		current.switchPlayers();
		old_states.add(current.cloneSession());
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
