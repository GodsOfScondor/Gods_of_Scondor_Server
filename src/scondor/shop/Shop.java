package scondor.shop;

import java.util.ArrayList;
import java.util.Random;

import scondor.deck.card.CardData;
import scondor.deck.card.CardLoader;
import scondor.gnet.server.ClientModel;
import scondor.packets.CardList;
import scondor.packets.Message;
import scondor.player.PlayerData;

public class Shop {
	
	private static final int SHOP_SUCCES = 5;
	private static final int SHOP_FAIL = 6;
	private static String packType;
	private static ArrayList<CardData> products = new ArrayList<>();
	
	public static boolean buy(ClientModel client, PlayerData data, String stuff) {
		packType = stuff.toUpperCase();
		int cost = getCosts(PackType.valueOf(packType));
		
		if (data.getMoney()>=cost) {
			data.editMoney(-cost, client);
			getProducts(client);
			client.sendPacket(new Message(""+SHOP_SUCCES));
			return true;
		} else {
			client.sendPacket(new Message(""+SHOP_FAIL));
			return false;
		}
	}
	
	private static void getProducts(ClientModel client){
		Random rand = new Random();
		int cardAmount=CardLoader.getSize();
		products.clear();
		
		switch(packType) {
		case "I_CLOSED": 
			products.add(CardLoader.getCardData(rand.nextInt(cardAmount)));
			break;
		case "I_OPEN":
			//give certain card
			break;
		case "X_CLOSED":
			for(int i = 0; i<10; i++) {
			products.add(CardLoader.getCardData(rand.nextInt(cardAmount)));
			}
			break;
		}
		System.out.println("Gesendetes Packet: shop;"+packType);
		client.sendPacket(new CardList(products, "shop;" + packType)); //bekommt man auf der client wieder zurück
	}
	
	private static int getCosts(PackType type) {
		switch(type) {
		case I_CLOSED: return 100;
		case I_OPEN: return 200;
		case X_CLOSED: return 300;
		}
		return 0;
	}
	
}
