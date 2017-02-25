package scondor.licenses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import scondor.Console;
import scondor.Database;

public class LicenseCreator {

	private static Random rand = new Random();

	public static void generateLicenses(int anzahl) {

		for (int z = 0; z < anzahl; z++) {
			String license = "";
			int r;
			for (int n = 0; n < 5; n++) {
				for (int i = 0; i < 4; i++) {
					r = rand.nextInt(36);

					if (r >= 10) {
						license = license + ((char) ('A' + (r - 10)));
					} else {
						license = license + ((char) ('0' + (r)));
					}
				}
				license = license + "-";
			}
			license = license.substring(0, license.length() - 1);
			
			ResultSet result = Database.query("SELECT * FROM GOS_FREELICENSES");
			boolean unique = true;
			try {
				while(result.next()) {
					if (result.getString("License").equalsIgnoreCase(license)) unique = false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if (unique) Database.execute("INSERT INTO GOS_FREELICENSES (LICENSE) VALUES ('" + license + "');");

		}
		Console.info("Created " + anzahl + " new licenses!");
	}
}
