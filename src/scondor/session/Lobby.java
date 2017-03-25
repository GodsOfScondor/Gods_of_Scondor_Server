package scondor.session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import scondor.Console;
import scondor.Database;
import scondor.deck.Deck;
import scondor.gnet.server.ClientModel;
import scondor.player.Player;
import scondor.player.PlayerMaster;

public class Lobby {

	private static Map<Player, Integer> lobby = new HashMap<>();
	private static Map<Player, Integer> ranked = new HashMap<>();
	private static Map<Player, Integer> online = new HashMap<>();
	private static Map<Player, Integer> custom = new HashMap<>();
	private static Map<Player, Integer> players;

	/**
	 * 
	 * player chose deck
	 * 
	 */
	public static void join(Player player, int deck) {
		
		Console.info(player.getData().getUsername() + " added to lobby with deck " + deck);
		lobby.put(player, deck);

	}
	
	/**
	 * 
	 * player chose game type | timer starts
	 * 
	 */
	public static void search(ClientModel client, GameType type) {
		
		Player player = PlayerMaster.getPlayerUUID(client.getUUID());

		if (player == null) {
			Console.error("Player is not online. (" + client.getUUID() + ")");
			return;
		}

		int deck = lobby.get(player);
		lobby.remove(player);
		players = getPlayers(type);
		players.put(player, deck);

		Console.info(player.getData().getUsername() + " joined lobby. (" + type.toString().toUpperCase() + "|"
				+ players.size() + ")");

		
		execute(generateTimer(player, type));
		
	}
	
	/**
	 * 
	 * 
	 * 
	 */
	public static TimerTask generateTimer(final Player player, final GameType type) {
		players = getPlayers(type);
		
		if (!players.containsKey(player)) {
			Console.info("Stopped searching for player " + player.getData().getUsername());
			return null;
		}
		
		return new TimerTask() {
			public void run() {
				
				if (players.size()>1) {
					buildSession(player, type);
				} else {
					Console.info("No enemy found for player " + player.getData().getUsername());
					execute(generateTimer(player, type));
				}
				
			}
		};
	}
	
	/**
	 * 
	 * builds session
	 * 
	 */
	private static void buildSession(Player player, GameType type) {
		
		players = getPlayers(type);
		
		Player enemy = getEnemy(player, type);

		if (player.getData().getDecks().size()<players.get(player)
			&& enemy.getData().getDecks().size()<players.get(enemy)) {
			
			leaveQueue(player);
			leaveQueue(enemy);
			
			// TODO throw error to clients
			Console.error("Invalid session!("+player.getData().getUsername()+"|"+enemy.getData().getUsername()+")");
			
			return;
			
		}
		
		Deck player_deck = new Deck(player.getData().getDecks().get(players.get(player)), player.getData());
		PlayerSide player_side = new PlayerSide(player, player_deck);

		Deck enemy_deck = new Deck(enemy.getData().getDecks().get(players.get(enemy)), enemy.getData());
		PlayerSide enemy_side = new PlayerSide(enemy, enemy_deck);

		SessionMaster.createSession(player_side, enemy_side, type);
		
		leaveQueue(player);
		leaveQueue(enemy);
		
		Console.info(player.getData().getUsername() + " started game with " + enemy.getData().getUsername() + "! (" + type.toString().toUpperCase() + ")");
	}
	
	/**
	 * 
	 * get deck id origin
	 * 
	 */
	public static int getDeckIDOrigin(Player player) {
		int min_id = -1;
		
		try {
			ResultSet deck_offset = Database.query("SELECT MIN(DECK_ID) AS ID FROM GOS_DECKS WHERE ID="+player.getData().getPlayerID()+" LIMIT 1");
			while(deck_offset.next()) min_id = deck_offset.getInt("ID");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return min_id;
	}
	
	/**
	 * 
	 * get a list of enemies for player based on game type
	 * 
	 */
	private static Map<Player, Integer> getPlayers(GameType type) {
		switch (type) {
		case CUSTOM:
			return custom;
		case ONLINE:
			return online;
		case RANKED:
			return ranked;
		}
		return null;
	}

	private static void execute(TimerTask task) {

		if (task != null) new Timer().schedule(task, 3000);

	}
	
	/**
	 *
	 * matches perfect enemy based on elo and mmr
	 * 
	 */
	private static Player getEnemy(Player player, GameType type) {

		Player enemy = null;

		players = getPlayers(type);

		for (Player p : players.keySet()) {

			if (!p.getData().getUsername().equals(player.getData().getUsername())) {

				/*
				 * dei part langs
				 * - kaust de methode auch verändern waunst wüst
				 */

				enemy = p;

			}

		}

		return enemy;

	}
	
	/**
	 * 
	 * player leaves lobby
	 * 
	 */
	public static void leaveQueue(Player p) {
		Console.info(p.getData().getUsername()+" has left the lobby!");
		lobby.remove(p);
		ranked.remove(p);
		online.remove(p);
		custom.remove(p);
	}

}
