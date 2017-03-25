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
	
	public void updateClient(ClientModel client) {
		this.client = client;
	}

	public PlayerData getData() {
		return data;
	}
	
}
