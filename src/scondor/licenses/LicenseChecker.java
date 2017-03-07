package scondor.licenses;

import java.sql.ResultSet;
import java.sql.SQLException;

import scondor.Console;
import scondor.Database;
import scondor.gnet.server.ClientModel;
import scondor.packets.Message;
import scondor.player.Player;
import scondor.player.PlayerData;
import scondor.player.PlayerMaster;

public class LicenseChecker {
	
	public static void login(ClientModel client, String username, String password) {
		
		try {
			
			ResultSet result = Database.query(
					"SELECT DISTINCT * FROM GOS_USER "
					+ "INNER JOIN GOS_PLAYER "
					+ "ON "
					+ "GOS_PLAYER.ID=GOS_USER.ID AND "
					+ "GOS_USER.NAME='"+username+"' AND "
					+ "GOS_USER.PASSWORD='"+password+"'");
			
			if (PlayerMaster.getPlayer(username)!=null) {
				
				// user already logged in
				Console.info(client.getUUID() + ": " + username + " failed to login. (user already logged in: "+username+")");
				client.sendPacket(new Message("0"));
				
			}
			
			else if (!result.next()) {
				
				// wrong pwd username combo
				Console.warn(client.getUUID() + ": " + username + " failed to login. (wrong password: "+password+")");
				client.sendPacket(new Message("1"));
				
			} else {
				
				int id = result.getInt("ID");
				int level = result.getInt("LEVEL");
				int money = result.getInt("MONEY");
				int elo = result.getInt("ELO");
				int xp = result.getInt("XP");
				String license = result.getString("LICENSE");
				
				// get wins and loses
				ResultSet stats = Database.query("SELECT DISTINCT * FROM GOS_STATS WHERE ID='"+id+"'");
				
				int wins = -1;
				int loses = -1;
				
				while (stats.next()) {
					wins = stats.getInt("Wins");
					loses = stats.getInt("Loses");
				}
				
				// create player data
				PlayerData data = new PlayerData(id, username, password, license, level, money, elo, xp, wins, loses);
				
				// add player to others...
				PlayerMaster.add(new Player(client, data));
				Console.info( + client.getUUID() + ": " + username + " has succesfully logged in.");
				client.sendPacket(new Message("2"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void register(ClientModel client, String username, String password, String license) {
		
		try {
			
			ResultSet result = Database.query(
					"SELECT DISTINCT * FROM GOS_FREELICENSES "
					+ "WHERE LICENSE='"+license+"'");
			
			if (!result.next()) {
				client.sendPacket(new Message("3"));
				Console.warn(client.getUUID() + ": " +username + " failed to register. (wrong license: "+license+")");
			} else {
				
				ResultSet names = Database.query("SELECT NAME FROM GOS_USER");
				
				while (names.next()) {
					if (names.getString("NAME").equals(username)) {
						//
						client.sendPacket(new Message("4"));
						Console.warn(client.getUUID() + ": " +username + " failed to register. (username already exists: "+username+")");
					}
				}
				
				Database.execute("INSERT INTO GOS_USER (`ID`, `LICENSE`, `NAME`, `PASSWORD`) VALUES (NULL, '"+license+"', '"+username+"', '"+password+"')");
				Database.execute("DELETE FROM GOS_FREELICENSES WHERE LICENSE='"+license+"'");
				
				int id = -1;
				ResultSet id_result = Database.query("SELECT DISTINCT ID FROM GOS_USER WHERE NAME='"+username+"'");
				while (id_result.next()) id = id_result.getInt("ID");
				
				Database.execute("INSERT INTO GOS_PLAYER (`ID`, `LEVEL`, `XP`, `MONEY`) VALUES ('"+id+"', '1', '0', '500')");
				Database.execute("INSERT INTO GOS_DECKS (`DECK_ID`, `ID`, `NAME`) VALUES ('0','"+id+"', 'Starterdeck')");
				Database.execute("INSERT INTO GOS_STATS (`ID`, `WINS`, `LOSES`, `DRAWS`) VALUES ('"+id+"', '0', '0', '0')");
				
				login(client, username, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
