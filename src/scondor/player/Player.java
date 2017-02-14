package scondor.player;

import scondor.gnet.server.ClientModel;

public class Player {
	
	private ClientModel client;
	private PlayerData data;
	
	public Player(ClientModel client, PlayerData data) {
		this.client = client;
		this.data = data;
	}

	public ClientModel getClient() {
		return client;
	}

	public PlayerData getData() {
		return data;
	}
	
}
