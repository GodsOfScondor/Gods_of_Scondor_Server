package scondor.session;

import java.util.ArrayList;
import java.util.List;

public class SessionMaster {
	
	private static List<SessionHistory> sessions = new ArrayList<>();
	
	public static void init() {
		
	}
	
	/*
	 * TODO MAKE SESSION CREATOR
	 */
	protected static void createSession(PlayerSide ps1, PlayerSide ps2) {
		sessions.add(new SessionHistory(new Session(ps1, ps2)));
	}
	
}