package scondor.licenses;

import java.sql.ResultSet;
import java.sql.SQLException;

import scondor.Database;
import scondor.player.PlayerData;

public class LicenseChecker {
	
	public static PlayerData login(String username, String password) {
		
		try {
			
			ResultSet result = Database.query(
					"SELECT DISTINCT * FROM GOS_USER "
					+ "INNER JOIN GOS_PLAYER "
					+ "ON "
					+ "GOS_PLAYER.ID=GOS_USER.ID AND "
					+ "GOS_USER.NAME='"+username+"' AND "
					+ "GOS_USER.PASSWORD='"+password+"'");
			
			if (!result.next()) {
				return null;
			} else {
				return new PlayerData(username, password, result.getString("LICENSE"), result.getInt("LEVEL"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static boolean register(String username, String password, String license) {
		
		try {
			
			ResultSet result = Database.query(
					"SELECT DISTINCT * FROM GOS_FREELICENSES "
					+ "WHERE LICENSE='"+license+"'");
			
			if (!result.next()) {
				return false;
			} else {
				Database.execute("INSERT INTO GOS_PLAYER (`ID`, `LEVEL`, `XP`) VALUES (NULL, '1', '0')");
				Database.execute("INSERT INTO GOS_USER (`ID`, `LICENSE`, `NAME`, `PASSWORD`) VALUES (NULL, '"+license+"', '"+username+"', '"+password+"')");
				login(username, password);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
