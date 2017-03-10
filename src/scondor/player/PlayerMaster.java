package scondor.player;

import java.util.ArrayList;
import java.util.List;

public class PlayerMaster {
	
	private static List<Player> players = new ArrayList<>();
	
	public static void add(Player player) {
		players.add(player);
	}
	
	public static void remove(Player player) {
		players.remove(player);
	}
	
	public static Player getPlayer(String username) {
		for (Player player : players) if (player.getData().getUsername().equals(username)) return player;
		return null;
	}
	
	public static Player getPlayer(int uuid) {
		for (Player player : players) if (player.getClient().getUUID()==uuid) return player;
		return null;
	}

	public static void saveAll() {
		for (Player player : players) {
			player.getData().getCards().save();
		}
	}
	
}
