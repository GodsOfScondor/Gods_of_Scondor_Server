package scondor.player;

import scondor.gnet.server.ClientModel;
import scondor.packets.Message;

public class Shop {
	
	private static final int SHOP_SUCCES = 5;
	private static final int SHOP_FAIL = 6;
	
	public static boolean buy(ClientModel client, PlayerData data, int cost) {
		
		if (data.getMoney()>=cost) {
			data.editMoney(-cost);
			client.sendPacket(new Message(""+SHOP_SUCCES));
			return true;
		} else {
			client.sendPacket(new Message(""+SHOP_FAIL));
			return false;
		}
		
	}
	
}
