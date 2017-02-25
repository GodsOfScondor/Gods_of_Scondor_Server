package scondor.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import scondor.player.Player;

public class Lobby {
	
	private static List<Player> ranked = new ArrayList<>();
	private static List<Player> online = new ArrayList<>();
	private static List<Player> custom = new ArrayList<>();
	private static List<Player> players;
	private static TimerTask task;
	
	public static void search(Player player, GameType type) {
		
		players = null;
		switch(type) {
		case CUSTOM: players = custom; break;
		case ONLINE: players = online; break;
		case RANKED: players = ranked; break;
		}
		
		if (players.size() == 0) {
			players.add(player);
		} else {
			task = new TimerTask() {
				@Override
				public void run() {
					
					if (players.size()<2) schedule(task);
					else {
						Player enemy =  getEnemy(player, type);
						
						new Session(new SessionState(new GamePlayer(player, null), new GamePlayer(enemy, null)));
						
					}
					
				}
			};
			
			schedule(task);
		}
	}
	
	private static void schedule(TimerTask task) {
		
		new Timer().schedule(task, 30000);
		
	}
	
	private static Player getEnemy(Player player, GameType type) {
		
		Player enemy = null;
		
		players = null;
		switch(type) {
		case CUSTOM: players = custom; break;
		case ONLINE: players = online; break;
		case RANKED: players = ranked; break;
		}
		
		for (Player p : players) {
			
			if (!p.getData().getUsername().equals(player.getData().getUsername())) {
				
				//TODO vergleiche elos und suche passenden spieler
				
				enemy = p;
				
			}
			
		}
		
		return enemy;
		
	}
	
	public static void searchCustom(Player p) {
		
	}
	
	public static void leaveQueue(Player p) {
		ranked.remove(p);
		online.remove(p);
		custom.remove(p);
	}
	
}
