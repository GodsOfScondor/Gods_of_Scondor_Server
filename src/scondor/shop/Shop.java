package scondor.shop;

import scondor.gnet.server.ClientModel;
import scondor.packets.Message;
import scondor.player.PlayerData;

public class Shop {
	
	private static final int SHOP_SUCCES = 5;
	private static final int SHOP_FAIL = 6;
	
	public static boolean buy(ClientModel client, PlayerData data, String stuff) {
		
		int cost = getCosts(PackType.valueOf(stuff.toUpperCase()));
		
		if (data.getMoney()>=cost) {
			data.editMoney(-cost, client);
			client.sendPacket(new Message(""+SHOP_SUCCES));
			return true;
		} else {
			client.sendPacket(new Message(""+SHOP_FAIL));
			return false;
		}
		
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
