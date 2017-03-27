package scondor.session;

import java.util.ArrayList;
import java.util.List;

import scondor.Console;
import scondor.packets.Message;

public class SessionMaster {
	
	private static List<SessionHistory> sessions = new ArrayList<>();
	
	public static void init() {
		
	}
	
	/*
	 * sends player and enemy init informations
	 */
	protected static void createSession(PlayerSide ps1, PlayerSide ps2, GameType type) {
		Session session = new Session(0,ps1, ps2);
		ps1.send(new Message("fight;start;" + type.toString().toLowerCase() + ";" + ps2.getPlayer().getData().getUsername()));
		ps2.send(new Message("fight;start;" + type.toString().toLowerCase() + ";" + ps1.getPlayer().getData().getUsername()));
		ps1.send((session.createState("start state")));
		ps2.send((session.createState("start state")));
		sessions.add(new SessionHistory(session, type));
	}
	
	/*
	 * closes a session
	 */
	public static void closeSession(int uuid) {
		SessionHistory remove = null;
		for (SessionHistory session : sessions) {
			if (session.getSession().getPlayer().getPlayer().getClient().getUUID()==uuid
					|| session.getSession().getEnemy().getPlayer().getClient().getUUID()==uuid) {
				remove = session;
				break;
			}
		}
		if (remove!=null) {
			Console.warn("Session closed suddenly. ("+remove.getPlayer().getData().getUsername()+"|"+remove.getEnemy().getData().getUsername()+"|"+remove.getID()+"|"+remove.getGameType().toString().toUpperCase()+")");
			sessions.remove(remove);
			if (remove.getPlayer().getClient().getUUID()!=uuid) remove.getSession().getPlayer().send(new Message("fight;exit"));
			if (remove.getEnemy().getClient().getUUID()!=uuid) remove.getSession().getEnemy().send(new Message("fight;exit"));
		}
	}
	
}