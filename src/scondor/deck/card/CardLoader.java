package scondor.deck.card;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import scondor.deck.card.fcode.FCode;
import scondor.deck.card.fcode.FCodeLoader;
import scondor.deck.card.troops.ATCard;
import scondor.deck.card.troops.DTCard;
import scondor.deck.card.troops.TroopCardData;
import scondor.mana.ManaType;

public class CardLoader {

	private static Card<?>[] cards = new Card<?>[1024];
	private static final String PATH = "data/cards.dat";

	private static BufferedReader reader;
	private static String line;
	private static String buffer[];

	private static int id;
	private static int mana_cost;
	private static String name;
	private static String description;
	private static ManaType mana_type;
	
	private static int attack;
	private static int live;
	private static int countdown;

	private static List<String> raw_data = new ArrayList<>();
	private static FCode fcode;

	/**
	 * 
	 * load raw data from card lib
	 * 
	 */
	public static void load() {

		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(PATH))));

			while ((line = reader.readLine()) != null) {

				if (!line.isEmpty()) {

					raw_data.add(line);

					if (line.startsWith("[")) {
						id = Integer.parseInt(line.substring(1, line.length() - 1));
					} else if (line.startsWith("name=")) {
						name = (line.split("=")[1]);
					} else if (line.startsWith("description=")) {
						description = (line.split("=")[1]);
					} else if (line.startsWith("manatype=")) {
						mana_type = ManaType.valueOf(line.split("=")[1]);
					} else if (line.startsWith("mana=")) {
						mana_cost = Integer.parseInt(line.split("=")[1]);
					} else if (line.startsWith("type=")) {

						line = line.split("=")[1];
						buffer = line.split(";");
						
						/*
						 * Attack Troop Card
						 */
						if (buffer[0].startsWith("atroop")) {
							
							attack = Integer.parseInt(buffer[1]);
							live = Integer.parseInt(buffer[2]);
							countdown = Integer.parseInt(buffer[3]);
							fcode = FCodeLoader.getFCode(Integer.parseInt(buffer[4]));
							
							cards[id] = new ATCard(new TroopCardData(id, name, description, mana_cost, mana_type,
									attack, live, countdown), fcode);
							
						} 
						/*
						 * Defense Troop Card
						 */
						else if (buffer[0].startsWith("dtroop")) {
							
							attack = Integer.parseInt(buffer[1]);
							live = Integer.parseInt(buffer[2]);
							fcode = FCodeLoader.getFCode(Integer.parseInt(buffer[3]));
							
							cards[id] = new DTCard(new TroopCardData(id, name, description, mana_cost, mana_type,
									attack, live, countdown), fcode);
							
						}

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
	public static CardData getCardData(int id) {
		return cards[id].getData();
	}

	/**
	 * 
	 * get card by id
	 * 
	 */
	public static Card<?> getCard(int id) {
		return cards[id];
	}

}
