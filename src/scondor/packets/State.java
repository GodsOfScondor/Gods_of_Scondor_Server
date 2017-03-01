package scondor.packets;

import scondor.gnet.packet.Packet;
import scondor.session.StateData;

public class State extends Packet {

	private static final long serialVersionUID = -1288298779687163575L;

	public State(StateData player1, StateData player2, String params) {
		super("GAMESTATE", 2);
		super.addEntry("PLAYER1", player1);
		super.addEntry("PLAYER2", player2);
		super.addEntry("PARAMS", params);
	}
	
}
