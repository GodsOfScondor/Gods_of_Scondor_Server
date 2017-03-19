package scondor.session;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import scondor.player.Player;

public class Lobby {
	
	private static Map<Player, Integer> lobby = new HashMap<>();
	private static Map<Player, Integer> ranked = new HashMap<>();
	private static Map<Player, Integer> online = new HashMap<>();
	private static Map<Player, Integer> custom = new HashMap<>();
	private static Map<Player, Integer> players;
	private static TimerTask task;
	
	public static void join(Player player, int deck) {
		
		lobby.put(player, deck);
		
	}
	
	public static void search(final Player player, final GameType type) {
		
		int deck = lobby.get(player);
		lobby.remove(player);
		players = getPlayers(type);
		players.put(player, deck);
		
		if (players.size() == 0) {
			
			// kana online
			
		} else {
			task = new TimerTask() {
				@Override
				public void run() {
					
					if (players.size()<2) schedule(task);
					else {
						Player enemy =  getEnemy(player, type);
						
						SessionMaster.createSession(new PlayerSide(player, null), new PlayerSide(enemy, null));
						
					}
					
				}
			};
			
			schedule(task);
		}
	}
	
	private static Map<Player, Integer> getPlayers(GameType type) {
		switch(type) {
		case CUSTOM: return custom;
		case ONLINE: return online;
		case RANKED: return ranked;
		}
		return  null;
	}

	private static void schedule(TimerTask task) {
		
		new Timer().schedule(task, 30000);
		
	}
	
	private static Player getEnemy(Player player, GameType type) {
		
		Player enemy = null;
		
		players = getPlayers(type);
		
		for (Player p : players.keySet()) {
			
			if (!p.getData().getUsername().equals(player.getData().getUsername())) {
				
				//TODO vergleiche elos und suche passenden spieler
				
				enemy = p;
				
			}
			
		}
		
		return enemy;
		
	}
	
	public static void leaveQueue(Player p) {
		lobby.remove(p);
		ranked.remove(p);
		online.remove(p);
		custom.remove(p);
	}
	
}
