package scondor.deck.card;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import scondor.deck.card.troops.AttackTroop;
import scondor.deck.card.troops.DefenseTroop;
import scondor.mana.ManaType;

public class CardMaster {

	private static Card[] cards = new Card[1024];
	private static final String PATH = "data/cards.dat";

	private static BufferedReader reader;
	private static String line;
	private static String buffer[];

	private static int id;
	private static int mana_cost;
	private static String name;
	private static ManaType mana_type;
	private static int attack;
	private static int live;
	private static int countdown;
	private static int fcode;

	/**
	 * 
	 * load raw data from card lib
	 * 
	 */
	public static void load() {
		
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(PATH))));
			
			while ((line = reader.readLine()) != null) {
				
				if (line.startsWith("[")) {
					id = Integer.parseInt(line.substring(1, line.length()-1));
				} else if (line.startsWith("name=")) {
					setName(line.split("=")[1]);
				} else if (line.startsWith("manatype=")) {
					mana_type = ManaType.valueOf(line.split("=")[1]);
				} else if (line.startsWith("mana=")) {
					mana_cost=Integer.parseInt(line.split("=")[1]);
				} else if(line.startsWith("type=")){
					
					line = line.split("=")[1];
					buffer = line.split(";");
					
					if (buffer[0].startsWith("atroop")) {
						attack = Integer.parseInt(buffer[1]);
						live = Integer.parseInt(buffer[2]);
						countdown=Integer.parseInt(buffer[3]);
						fcode=Integer.parseInt(buffer[4]);
						cards[id] = new AttackTroop(mana_cost, attack, live, mana_type, countdown, fcode);
					}else if(buffer[0].startsWith("dtroop")){
						attack = Integer.parseInt(buffer[1]);
						live = Integer.parseInt(buffer[2]);
						fcode=Integer.parseInt(buffer[3]);
						cards[id] = new DefenseTroop(mana_cost, attack, live, mana_type, fcode);
					}
					
				}
				
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
	public Card getCardData(int id) {
		return cards[id];
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		CardMaster.name = name;
	}

}
