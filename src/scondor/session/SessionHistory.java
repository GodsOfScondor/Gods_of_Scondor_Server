package scondor.session;

import java.util.ArrayList;
import java.util.List;

public class SessionHistory {
	
	private List<Session> old_states = new ArrayList<>();
	private Session current;
	private GameType type;
	
	public SessionHistory(Session start) {
		this.current = start;	
	}
	
	public Session getSession() {
		return current;
	}
	
	public GameType getGameType() {
		return type;
	}
	
	public List<Session> getOldStates() {
		return old_states;
	}
	
}
