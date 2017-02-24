package scondor.session;

import java.util.ArrayList;
import java.util.List;

public class SessionMaster {
	
	private static List<Session> sessions = new ArrayList<>();
	
	public static void init() {
		
	}
	
	/*
	 * TODO MAKE SESSION CREATOR
	 */
	protected static void createSession(Session session) {
		sessions.add(session);
	}
	
}
