package scondor.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import scondor.Console;
import scondor.packets.State;
import scondor.player.Player;

public class SessionController {
	
	private static final int ROUND_TIME = 5000;
	private static int count = 0;
	private int id;
	private int updates;
	private List<Session> old_states = new ArrayList<>();
	private Session current;
	private GameType type;
	private Timer timer = new Timer();
	
	public SessionController(Session start, GameType type) {
		this.current = start.cloneSession();
		this.old_states.add(start);
		this.id = count++;
		this.type = type;
		
		timer.schedule(generateTask(), ROUND_TIME);
		
	}
	
	private TimerTask generateTask() {
		return new TimerTask() {
			public void run() {
				update("time is out");
			}
		};
	}

	public int getID() {
		return id;
	}

	public void send(String params) {
		State state1 = current.createState(params, GameState.PLAYER1);
		State state2 = current.createState(params, GameState.PLAYER2);
		
		current.getMaster().send(state1);
		current.getSlave().send(state2);
	}
	
	public void update(String params) {
		
		send(params);
		
		current.switchPlayers();
		old_states.add(current.cloneSession());
		updates++;
		
		if (!params.equalsIgnoreCase("time is out")) timer.cancel();
		timer.schedule(generateTask(), ROUND_TIME);
		
		Console.info("Session updated: " + updates + "(" + current.getMaster().getPlayer().getData().getUsername());
	}
	
	public Session getSession() {
		return current;
	}
	
	public Player getPlayer() {
		return current.getPlayer().getPlayer();
	}
	
	public Player getEnemy() {
		return current.getEnemy().getPlayer();
	}
	
	public GameType getGameType() {
		return type;
	}
	
	public List<Session> getOldStates() {
		return old_states;
	}
	
}
