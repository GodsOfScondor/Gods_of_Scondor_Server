package scondor.player;

import java.util.List;

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

	public int getLevel() {
		return level;
	}
	
	public int getMoney() {
		return money;
	}
	
	public int getELO() {
		return elo;
	}
	
	public int getXP() {
		return xp;
	}
	
	public int getWins() {
		return wins;
	}
	
	public int getLoses() {
		return loses;
	}
	
	public void setWins(int wins) {
		this.wins = wins;
	}
	
	public void setLoses(int loses) {
		this.loses = loses;
	}
	
	public DeckData getDeck(int id) {
		for (DeckData deck : decks) if (deck.getID()==id) return deck;
		return null;
	}
	
	public List<DeckData> getDecks() {
		return decks;
	}
	
	public Cards getCards() {
		return cards;
	}
	
}