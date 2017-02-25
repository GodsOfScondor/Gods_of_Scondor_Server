package scondor.player;

public class PlayerData {
	
	private String username;
	private String password;
	private String license;
	private int level;
	private int money;
	private int elo;
	private int xp;
	
	public PlayerData(String username, String password, String license, int level, int money, int elo, int xp) {
		this.username = username;
		this.password = password;
		this.license = license;
		this.level = level;
		this.money = money;
		this.elo = elo;
		this.xp = xp;
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
	
}
