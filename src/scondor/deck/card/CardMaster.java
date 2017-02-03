package scondor.deck.card;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class CardMaster {
	
	private static CardData[] cards = new CardData[1024];
	private static final String PATH = "data/cards.dat";
	
	private static BufferedReader reader;
	private static String line;
	
	/**
	 * 
	 * load raw data from card lib
	 * 
	 */
	public static void load() {
		
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(PATH))));
			
			while ((line = reader.readLine()) != null) {
				
				//TODO load data
				
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * get card raw data by id
	 * 
	 */
	public CardData getCardData(int id) {
		return cards[id];
	}
	
}
