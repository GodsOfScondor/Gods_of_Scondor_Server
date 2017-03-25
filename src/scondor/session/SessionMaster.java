package scondor.session;

import java.util.ArrayList;
import java.util.List;

import scondor.packets.Message;

public class SessionMaster {
	
	private static List<SessionHistory> sessions = new ArrayList<>();
	
	public static void init() {
		
	}
	
	/*
	 * TODO MAKE SESSION CREATOR
	 */
	protected static void createSession(PlayerSide ps1, PlayerSide ps2, GameType type) {
		Session session = new Session(0,ps1, ps2);
		ps1.getPlayer().getClient().sendPacket(new Message("fight;" + type.toString().toLowerCase() + ";PS1;" + ps2.getPlayer().getData().getUsername()));
		ps2.getPlayer().getClient().sendPacket(new Message("fight;" + type.toString().toLowerCase() + ";PS2;" + ps1.getPlayer().getData().getUsername()));
		ps1.getPlayer().getClient().sendPacket((session.createState("start state")));
		ps2.getPlayer().getClient().sendPacket((session.createState("start state")));
		sessions.add(new SessionHistory(session, type));
	}
	
	public static void closeSession(int uuid) {
		SessionHistory remove = null;
		for (SessionHistory session : sessions) {
			if (session.getSession().getPlayer().getPlayer().getClient().getUUID()==uuid
					|| session.getSession().getEnemy().getPlayer().getClient().getUUID()==uuid) {
				remove = session;
				break;
			}
		}
		if (remove!=null) sessions.remove(remove);
		remove.getSession().getPlayer().getPlayer().getClient().sendPacket(new Message("fight;exit"));
		remove.getSession().getEnemy().getPlayer().getClient().sendPacket(new Message("fight;exit"));
	}
	
}