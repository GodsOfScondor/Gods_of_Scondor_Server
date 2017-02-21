package scondor.licenses;

import java.sql.ResultSet;
import java.sql.SQLException;

import scondor.CMDTool;
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
				System.out.println(CMDTool.WARN + client.getUUID() + ": " + username + " failed to login. (user already logged in: "+username+")");
				client.sendPacket(new Message("msg:1,0,0:Failed to login!"));
				
			}
			
			else if (!result.next()) {
				
				// wrong pwd username combo
				System.out.println(CMDTool.WARN + client.getUUID() + ": " + username + " failed to login. (wrong password: "+password+")");
				client.sendPacket(new Message("msg:1,0,0:Failed to login!"));
				
			} else {
				
				// create player data
				PlayerData data = new PlayerData(username, password, result.getString("LICENSE"), result.getInt("LEVEL"), result.getInt("MONEY"));
				
				// add player to others...
				PlayerMaster.add(new Player(client, data));
				System.out.println(CMDTool.INFO + client.getUUID() + ": " + username + " has succesfully logged in.");
				client.sendPacket(new Message("msg:0,1,0:2:Succesfully logged in!"));
				
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
				client.sendPacket(new Message("msg:1,0,0:Wrong License!"));
				System.out.println(CMDTool.WARN + client.getUUID() + ": " +username + " failed to register. (wrong license: "+license+")");
			} else {
				
				ResultSet names = Database.query("SELECT NAME FROM GOS_USER");
				
				while (names.next()) {
					if (names.getString("NAME").equals(username)) {
						//
						client.sendPacket(new Message("msg:1,0,0:Username already exists!"));
						System.out.println(CMDTool.WARN + client.getUUID() + ": " +username + " failed to register. (username already exists: "+username+")");
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
