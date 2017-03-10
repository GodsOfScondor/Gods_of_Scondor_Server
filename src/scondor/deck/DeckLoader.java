package scondor.deck;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import scondor.Database;
import scondor.god.GodLoader;

public class DeckLoader {
	
	private static BufferedReader reader;
	private static BufferedWriter writer;
	
	private final static String PATH="data/decks/";
	
	private static List<DeckData> decks = new ArrayList<>();
	
	private static int id;
	private static List<Integer> card_ids;
	private static int god_id;
	private static String name;
	
	/**
	 * 
	 * loads all raw decks
	 * 
	 */
	public static void load() {
		
		File[] files=new File(PATH).listFiles();
		
		for (File file : files) {
			if (file.exists() && file.isFile()) {
				load(file);
			}
		}
		
	}
	
	/**
	 * 
	 * reloads specific deck
	 * 
	 */
	public static void reload(DeckData deck) {
		load(save(deck));
	}
	
	/**
	 * 
	 * returns all raw decks a player has
	 * 
	 */
	public static List<DeckData> getDecks(int player_id) {
		
		List<DeckData> player_decks = new ArrayList<>();
		
		ResultSet result = Database.query("SELECT DECK_ID FROM GOS_DECKS WHERE ID="+player_id+"");
		
		try {
			while(result.next()) {
				player_decks.add(getDeck(result.getInt("DECK_ID")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return player_decks;
		
	}
	
	/**
	 * 
	 * clones a deck and claims it to specific player
	 * 
	 */
	public static int cloneDeck(int deck_id, int player_id, String new_name) {
		
		try {
			/**
			 * 
			 * TODO REWORK CODE!!!!!!!!!!
			 * 
			 */
			
			// target source file
			File src = new File("data/decks/D"+deck_id+".deck");
			
			// deck does not exist
			if (!src.exists()) return -1;
			
			// generate id
			int id = -1;
			ResultSet max_id = Database.query("SELECT MAX(ID) FROM GOS_DECKS LIMIT 1");
			while(max_id.next()) id = max_id.getInt("ID");
			id++;
			
			// error at generating id
			if (id==0) return -2;
			
			// create new file and database insert
			File dst = new File("data/decks/D"+id+".deck");
			Database.execute("INSERT INTO GOS_DECKS ('DECK_ID', 'ID', 'NAME)' values (NULL, )");
			
			// copy file
			Files.copy(src.toPath(), dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			// return new deck
			return id;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
		
	}
	
	/**
	 * 
	 * returns raw deck data via id
	 * 
	 */
	private static DeckData getDeck(int deck_id) {
		for (DeckData deck : decks) if (deck.getID()==deck_id) return deck;
		return null;
	}
	
	/**
	 * 
	 * loads one raw deck data
	 * 
	 */
	private static void load(File file) {
		
		if (file.exists()) {
			try {
				
				reader=new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				
				// get id from file name
				id=Integer.parseInt(file.getName().substring(1,file.getName().length()-5));
				
				// recreate card id list
				card_ids = new ArrayList<>();
				
				// read card ids from file
				for (String number : reader.readLine().split(",")) card_ids.add(Integer.parseInt(number));
				
				// read god id from file
				god_id = Integer.parseInt(reader.readLine());
				
				// get name of deck from DB
				name = "error while loading";
				
				ResultSet names = Database.query("SELECT NAME FROM GOS_DECKS WHERE DECK_ID='"+id+"'");
				while (names.next()) name = names.getString("NAME");
				
				// add raw deck data to list
				decks.add(new DeckData(id, name, card_ids, GodLoader.getGod(god_id)));
				
				reader.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static File save(DeckData deck) {
		
		File file = new File(PATH+"D"+deck.getID()+".deck");
		
		if (file.exists()) {
			
			try {
				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
				
				// writes card ids
				int n=0;
				for (int card_id : deck.getCards()) {
					if (++n!=deck.getCards().size()) {
						writer.write(card_id+",");
					} else {
						writer.write(card_id+"");
					}
				}
				
				// writes god id
				writer.newLine();
				writer.write("" + deck.getGod().getID());
				writer.newLine();
				
				writer.close();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return file;
			
		} else {
			
			return null;
			
		}
		
	}
	
}
