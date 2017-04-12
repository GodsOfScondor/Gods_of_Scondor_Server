package scondor;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import scondor.deck.DeckLoader;
import scondor.deck.card.fcode.FCodeLoader;
import scondor.licenses.LicenseChecker;
import scondor.licenses.LicenseCreator;
import scondor.player.PlayerData;
import scondor.player.PlayerMaster;

public class Console {

	private static Scanner s;
	private static String line;
	private static String[] parts;

	public static void send(String msg) {
		System.out.println(msg);
	}

	public static void info(String msg) {
		System.out.println(CMDTool.INFO + msg);
	}

	public static void warn(String msg) {
		System.out.println(CMDTool.WARN + msg);
	}

	public static void error(String msg) {
		System.out.println(CMDTool.ERROR + msg);
	}

	/*
	 * listen to console commands
	 */
	public static void listen() {

		// create scanner
		s = new Scanner(System.in);

		// listen to user input
		while ((line = s.next()) != null) {

			parts = line.split("_");

			if (line.equals("help")) {
				info("Server-Commands:\n");
				send("help                      | Shows all available Commands");
				send("create_licenses_a         | Automatically generates a licenses");
				send("createuser_a_b_c          | a: license b: username c: password");
				send("deleteuser_n              | Deletes user with name n from database.");
				send("clone_a_b_c               | a: deck_id, b: player_id, c:deck_name");
				send("deletedeck_a              | a: deck_id");
				send("reset                     | Resets cards, decks, wins, loses, money... of a player");
				send("save                      | Saves contents.\n\n");
			}
			// generates new licenses
			else if (line.startsWith("create_licenses_")) {
				LicenseCreator.generateLicenses(Integer.parseInt(parts[2]));
			}
			// executes fcode
			else if (line.startsWith("execute_")) {
				FCodeLoader.getFCode(Integer.parseInt(parts[1])).execute(null, null);
			}
			// deletes user
			else if (line.startsWith("deleteuser_")) {
				ResultSet result = Database.query("SELECT ID FROM GOS_USER WHERE NAME='" + parts[1] + "'");
				int id = -1;
				try {
					while (result.next())
						id = result.getInt("id");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				send("" + id);
				Database.execute("DELETE FROM `GOS_USER` WHERE ID='" + id + "'");
				Database.execute("DELETE FROM `GOS_PLAYER` WHERE ID='" + id + "'");
				Database.execute("DELETE FROM `GOS_DECKS` WHERE ID='" + id + "'");
				Database.execute("DELETE FROM `GOS_STATS` WHERE ID='" + id + "'");
			}
			// saves all
			else if (line.startsWith("save")) {
				ContentLoader.save();
			}
			// clones deck
			else if (line.startsWith("clone_")) {
				if (parts.length != 4) {
					error("Syntax error!");
					continue;
				}
				int result = DeckLoader.cloneDeck(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3]);
				if (result == -1)
					error("Deck does not exist!");
				else if (result == -2)
					error("Failed to generate ID!");
				else if (result == -3)
					error("Unknown error!");
				else
					info("Succesfully cloned deck. (new ID: " + result + ")");
			}
			// creates user
			else if (line.startsWith("createuser_")) {
				String license = parts[1];
				String name = parts[2];
				String password = parts[3];
				
				// TODO secure double names and license fails
				
				LicenseChecker.createUser(license, name, password);
				Console.info("Succesfully created user " + name + "! ("+license+")");
			}
			// deletes deck
			else if (line.startsWith("deletedeck_")) {
				if (parts.length != 2) {
					error("Syntax error!");
					continue;
				}
				int result = DeckLoader.deleteDeck(Integer.parseInt(parts[1]));
				if (result == -1)
					error("Deck does not exist!");
				else if (result == -2)
					error("Could not deleted deck. (ID: " + result + ")");
				else if (result == 1)
					info("Succesfully deleted deck. (ID: " + result + ")");
			}
			// reset user
			else if (line.startsWith("reset_")) {
				if (parts.length != 2) {
					error("Syntax error!");
					continue;
				}
				
				PlayerData data = PlayerMaster.getPlayer(Integer.parseInt(parts[1])).getData();
				data.reset();
				
			}
			// restarts server
			else if (line.startsWith("restart")) {
				execute("/home/Gods_of_Scondor/restart.sh");
			}
			// restarts server
			else if (line.startsWith("stop")) {
				execute("/home/Gods_of_Scondor/stop.sh");
			}
			send("");
		}

		s.close();

	}
	
	/*
	 * executes shell cmds
	 */
	private static void execute(String shellfile) {
		try {
			ProcessBuilder pb = new ProcessBuilder(shellfile);
			pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}