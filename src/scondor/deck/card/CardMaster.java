package scondor.deck.card;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class CardMaster {
	
	private static Card[] cards = new Card[1024];
	private static final String PATH = "data/cards.dat";
	
	private static BufferedReader reader;
	private static String line;
	
	public static void load() {
		
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(PATH))));
			
			while ((line = reader.readLine()) != null) {
				

				
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
