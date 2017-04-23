package scondor.session;

import java.util.ArrayList;
import java.util.List;

import scondor.Console;
import scondor.event.EventHandler;
import scondor.packets.Message;
import scondor.player.PlayerMaster;

public class SessionMaster {
	
	private static List<SessionController> sessions = new ArrayList<>();
	
	public static void init() {
		
	}
	
	/*
	 * sends player and enemy init informations
	 */
	protected static void createSession(PlayerSide ps1, PlayerSide ps2, EventHandler handler, GameType type) {
		Session session = new Session(0,ps1, ps2);
		
		ps1.send(new Message("fight;start;" + type.toString().toLowerCase() + ";" + ps2.getPlayer().getData().getUsername()));
		ps2.send(new Message("fight;start;" + type.toString().toLowerCase() + ";" + ps1.getPlayer().getData().getUsername()));
		
		ps1.send(new Message("fight;action;turn"));
		ps2.send(new Message("fight;action;wait"));
		
		SessionController controller = new SessionController(session, type, handler);
		
		sessions.add(controller);
		
		controller.send("start state");
	}
	
	/*
	 * closes a session
	 */
	public static SessionController closeSession(int uuid) {
		SessionController remove = null;
		for (SessionController session : sessions) {
			if (session.getSession().getPlayer().getPlayer().getClient().getUUID()==uuid
					|| session.getSession().getEnemy().getPlayer().getClient().getUUID()==uuid) {
				remove = session;
				break;
			}
		}
		if (remove!=null) {
			Console.warn("Session closed suddenly. ("+remove.getPlayer().getData().getUsername()+"|"+remove.getEnemy().getData().getUsername()+"|"+remove.getID()+"|"+remove.getGameType().toString().toUpperCase()+")");
			sessions.remove(remove);
			remove.close();
		}
		return remove;
	}
	
	/*
	 * send enemy player disconnect msg
	 */
	public static void sendEnemyQuitMSG(int uuid, SessionController controller) {
		if (controller!=null) {
			Message msg = new Message("fight;exit;"+EndOfGameType.QUIT.toString().toUpperCase());
			controller.getOpponent(PlayerMaster.getPlayerUUID(uuid)).getClient().sendPacket(msg);
		}
	}

	public static SessionController getSession(String player) {
		for (SessionController controller : sessions) {
			if (controller.getPlayer().getData().getUsername().equals(player)
					|| controller.getEnemy().getData().getUsername().equals(player)) {
				
				return controller;
				
			}
		}
		return null;
	}
	
}