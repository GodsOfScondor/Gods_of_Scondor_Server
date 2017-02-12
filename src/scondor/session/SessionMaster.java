package scondor.session;

import java.util.ArrayList;
import java.util.List;

public class SessionMaster {
	
	private static List<Session> sessions = new ArrayList<>();
	
	public static void init() {
		
	}
	
	protected static void add(Session session) {
		sessions.add(session);
	}
	
}
