package scondor.session;

import java.util.ArrayList;
import java.util.List;

public class Session {
	
	private List<SessionState> old_states = new ArrayList<>();
	private SessionState current;
	private GameType type;
	
	public Session() {
		
	}
	
	public SessionState getSession() {
		return current;
	}
	
	public GameType getGameType() {
		return type;
	}
	
	public List<SessionState> getOldStates() {
		return old_states;
	}
	
}
