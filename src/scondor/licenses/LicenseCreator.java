package scondor.licenses;

import java.util.Random;

import scondor.Database;

public class LicenseCreator {

	private static Random rand = new Random();

	public static void generateLicenses(int Anzahl) {
		String license = "";
		int r;

		for (int z = 0; z < Anzahl; z++) {

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
			Database.execute("INSERT INTO GOS_FREELICENSES (LICENSE) VALUES (" + license + ");");
			//KEINE DUPLIKATE!!!
		}
	}
}
