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
import scondor.deck.card.Card;
import scondor.deck.card.CardLoader;
import scondor.god.GodData;
import scondor.god.GodLoader;

public class DeckLoader {
	
	private static BufferedReader reader;
	private static BufferedWriter writer;
	
	private final static String PATH="data/decks/";
	
	private static int counter=0;
	private static int id;
	
	private static String[] cards=new String[60];
	private static GodData god;
	
	private static List<Deck> decks = new ArrayList<>();
	
	public static void load() {
		File[] files=new File(PATH).listFiles();
		
		counter=0;
		while(counter<files.length){
			if (files[counter].isFile()) {
				load(files[counter]);
				counter++;
			} else {
				files[counter].delete();
			}
		}
		
	}
	
	public static void reload(Deck deck) {
		load(save(deck));
	}
	
	private static void load(File file) {
		
		if (file.exists()) {
			try {
				
				reader=new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				
				id=Integer.parseInt(file.getName().substring(1,file.getName().length()-5));
				
				cards=reader.readLine().split(",");
				god=GodLoader.getGod(Integer.parseInt(reader.readLine()));
				
//				decks.add(new Deck(id, god));
				
				for(String s: cards){
					if (!s.equals("END")) decks.get(counter).addCard(CardLoader.getCardData(Integer.parseInt(s)));
				}
				
				reader.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static File save(Deck deck) {
		
		File file = new File(PATH+"D"+deck.getID()+".deck");
		
		if (file.exists()) {
			
			try {
				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
				
				for (Card<?> card : deck.getCards()) {
					writer.write(card.getData().getID()+",");
				}
				writer.write("END");
				writer.newLine();
				writer.write(deck.getGod().getData().getID());
				
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
	
	public static List<Deck> getDecks(int id) {
		
		List<Deck> player_decks = new ArrayList<>();
		
		ResultSet result = Database.query("SELECT DECK_ID FROM GOS_DECKS WHERE ID="+id+"");
		
		try {
			while(result.next()) {
				player_decks.add(getDeck(result.getInt("DECK_ID")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return player_decks;
		
	}
	
	public static int cloneDeck(int deck_id) {
		
		try {
			
			File src = new File("data/decks/D"+deck_id+".deck");
			File dst = new File("data/decks/D"+deck_id+".deck");
			
			// deck does not exist
			if (!src.exists()) return -1;
			
			// generate id
			int id = -1;
			
			// error at generating id
			if (id==-1) return -2;
			
			// copy file
			Files.copy(src.toPath(), dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			// return new deck
			return id;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return -1;
		
	}
	
	private static Deck getDeck(int deck_id) {
		for (Deck deck : decks) if (deck.getID()==deck_id) return deck;
		return null;
	}
	
}
