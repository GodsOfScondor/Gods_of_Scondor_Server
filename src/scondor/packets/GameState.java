package scondor.packets;

import scondor.gnet.packet.Packet;
import scondor.session.SessionState;

public class GameState extends Packet {

	private static final long serialVersionUID = -1288298779687163575L;

	public GameState(SessionState session, String params) {
		super("GAMESTATE", 2);
		super.addEntry("SESSION", session);
		super.addEntry("PARAMS", params);
	}
	
}
