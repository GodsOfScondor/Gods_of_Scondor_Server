package scondor.session;

import java.util.ArrayList;
import java.util.List;

import scondor.player.Player;

public class Lobby {
	
	private static List<Player> lobby = new ArrayList<>();
	
	public static void add(Player p) {
		lobby.add(p);
	}
	
	public static void remove(Player p) {
		lobby.remove(p);
	}
	
}
