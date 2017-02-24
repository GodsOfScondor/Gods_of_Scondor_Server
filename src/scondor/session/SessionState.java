package scondor.session;

public class SessionState {
	
	private GamePlayer p1, p2;
	
	public SessionState(GamePlayer p1, GamePlayer p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public GamePlayer getPlayer(GameState state) {
		switch (state) {
			case PLAYER1: return p1;
			case PLAYER2: return p2;
		}
		return null;
	}
}
