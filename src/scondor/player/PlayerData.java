package scondor.player;

import java.util.List;

import scondor.Console;
import scondor.Database;
import scondor.deck.DeckData;
import scondor.deck.DeckLoader;

public class PlayerData {
	
	private int id;

	private String username;
	private String password;
	private String license;
	private int level;
	private int money;
	private int elo;
	private int xp;
	
	private int wins;
	private int loses;
	
	private List<DeckData> decks;
	private Cards cards;
	
	/**
	 * 
	 * represents whole data of a player
	 * 
	 */
	public PlayerData(int id, String username, String password, String license, int level, int money, int elo, int xp, int wins, int loses) {
		
		this.id = id;
		
		this.username = username;
		this.password = password;
		this.license = license;
		this.level = level;
		this.money = money;
		this.elo = elo;
		this.xp = xp;
		this.wins = wins;
		this.loses = loses;
		
		decks = DeckLoader.getDecks(id);
		cards = new Cards(id);
	}
	
	/**
	 * 
	 * user data (id, username, password, license)
	 * 
	 */
	
	public int getPlayerID() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getLicense() {
		return license;
	}
	
	/**
	 * 
	 * player data (level, money, elo, xp)
	 * 
	 */

	public int getLevel() {
		return level;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void editMoney(int money) {
		this.money+=money;
		Database.execute("UPDATE GOS_PLAYER SET MONEY="+this.money+" WHERE ID='"+id+"'");
	}
	
	public void setMoney(int money) {
		this.money=money;
		Database.execute("UPDATE GOS_PLAYER SET MONEY="+this.money+" WHERE ID='"+id+"'");
	}
	
	public int getELO() {
		return elo;
	}
	
	public int getXP() {
		return xp;
	}
	
	/**
	 * 
	 * player stats (wins, loses)
	 * 
	 */
	
	public int getWins() {
		return wins;
	}
	
	public int getLoses() {
		return loses;
	}
	
	public void incWins() {
		this.wins++;
		Database.execute("UPDATE GOS_STATS SET WINS="+this.wins+" WHERE ID='"+id+"'");
	}
	
	public void resetWins() {
		this.wins=0;
		Database.execute("UPDATE GOS_STATS SET WINS="+this.wins+" WHERE ID='"+id+"'");
	}
	
	public void incLoses() {
		this.loses++;
		Database.execute("UPDATE GOS_STATS SET LOSES="+this.loses+" WHERE ID='"+id+"'");
	}
	
	public void resetLoses() {
		this.loses=0;
		Database.execute("UPDATE GOS_STATS SET LOSES="+this.loses+" WHERE ID='"+id+"'");
	}
	
	public DeckData getDeck(int id) {
		for (DeckData deck : decks) if (deck.getID()==id) return deck;
		return null;
	}
	
	/**
	 * 
	 * @return raw decks of a player
	 * 
	 */
	public List<DeckData> getDecks() {
		return decks;
	}
	
	/**
	 * 
	 * @return avaible cards of a player
	 * 
	 */
	public Cards getCards() {
		return cards;
	}

	public void reload() {
		decks = DeckLoader.getDecks(id);
		cards = new Cards(id);
	}

	public void reset() {
		// deletes all decks of a player
		for (DeckData deck : decks) {
			int result = DeckLoader.deleteDeck(deck.getID());
			if (result == -1)
				Console.error("Deck does not exist!");
			else if (result == -2)
				Console.error("Could not deleted deck. (ID: " + result + ")");
			else if (result == 1)
				Console.info("Succesfully deleted deck. (ID: " + result + ")");
		}
		// delete cards of a player
		getCards().reset();
		
		// reset wins and loses
		resetWins();
		resetLoses();
		
		// try to reload player data
		reload();
	}
	
}