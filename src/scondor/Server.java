package scondor;

import org.gnet.packet.Packet;
import org.gnet.server.ClientModel;
import org.gnet.server.ServerEventListener;

import scondor.color.CMDTool;

public class Server extends ServerEventListener {

	@Override
	protected void clientConnected(ClientModel client) {
		System.out.println(CMDTool.INFO + " " + client.getUuid() + " has connected!");
	}

	@Override
	protected void clientDisconnected(ClientModel client) {
		System.out.println(CMDTool.INFO + " " + client.getUuid() + " has disconnected!");
	}

	@Override
	protected void debugMessage(String msg) {
		System.out.println(msg);
	}

	@Override
	protected void errorMessage(String msg) {
		System.out.println(msg);
	}

	@Override
	protected void packetReceived(ClientModel client, Packet packet) {
		
	}
	
}
