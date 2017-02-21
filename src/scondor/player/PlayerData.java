package scondor.player;

public class PlayerData {
	
	private String username;
	private String password;
	private String license;
	private int level;
	private int money;
	
	public PlayerData(String username, String password, String license, int level, int money) {
		this.username = username;
		this.password = password;
		this.license = license;
		this.level = level;
		this.money = money;
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
	
}
