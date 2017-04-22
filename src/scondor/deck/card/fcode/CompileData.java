package scondor.deck.card.fcode;

import scondor.session.PlayerSide;
import scondor.session.SessionController;

public class CompileData {
	
	private PlayerSide player;
	private PlayerSide enemy;
	private TargetType type;
	
	public CompileData(SessionController controller, TargetType type) {
		this.player = controller.getSession().getPlayer();
		this.enemy = controller.getSession().getEnemy();
		this.type = type;
	}

	public PlayerSide getPlayer() {
		return player;
	}

	public PlayerSide getEnemy() {
		return enemy;
	}

	public TargetType getType() {
		return type;
	}
	
}
