package scondor.deck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import scondor.deck.card.CardMaster;
import scondor.god.GodData;
import scondor.mana.ManaType;

public class DeckLoader {
	
	private final static String path="data/decks/";
	private static File[] files;
	private static File file;
	private static int counter=0;
	private static int id;
	private static String[] cards=new String[60];
	private static BufferedReader br;
	private static String[] god;
	
	private static List<DeckData> decks = new ArrayList<>();
	
	public static void load() {
		files=new File(path).listFiles();
		
		while(files.length>counter){
			file=files[counter];
			try {
				br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				id=Integer.parseInt(file.getName().substring(1,file.getName().length()-5));
				
				cards=br.readLine().split(",");
				god=br.readLine().split(",");
				
				decks.add(new DeckData(id, new GodData(Integer.parseInt(god[2]),Integer.parseInt(god[3])
						,ManaType.valueOf(god[0]),ManaType.valueOf(god[1]))));
				
				for(String s: cards){
					decks.get(counter).addCard(CardMaster.getCardData(Integer.parseInt(s)));
				}
				
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
			counter++;
			System.out.println(counter);
		}
		
	}
	
	public static DeckData getDeck() {
		return null;
	}
	
}
