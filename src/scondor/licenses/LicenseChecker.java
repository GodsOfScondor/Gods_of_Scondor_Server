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
				
				// create player data
				PlayerData data = new PlayerData(username, password, result.getString("LICENSE"), result.getInt("LEVEL"), result.getInt("MONEY"), result.getInt("ELO"), result.getInt("XP"));
				
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
				//
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
				
				Database.execute("DELETE FROM GOS_FREELICENSES WHERE LICENSE='"+license+"'");
				Database.execute("INSERT INTO GOS_PLAYER (`ID`, `LEVEL`, `XP`, `MONEY`) VALUES (NULL, '1', '0', '500')");
				Database.execute("INSERT INTO GOS_USER (`ID`, `LICENSE`, `NAME`, `PASSWORD`) VALUES (NULL, '"+license+"', '"+username+"', '"+password+"')");
				
				login(client, username, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
