package scondor.deck.card.fcode;

import scondor.session.PlayerSide;

public class CompileData {
	
	private PlayerSide player;
	private PlayerSide enemy;
	private int target;
	private TargetType type;
	
	public CompileData(PlayerSide player, PlayerSide enemy, int target, TargetType type) {
		this.player = player;
		this.enemy = enemy;
		this.target = target;
		this.type = type;
	}

	public PlayerSide getPlayer() {
		return player;
	}

	public PlayerSide getEnemy() {
		return enemy;
	}

	public int getTarget() {
		return target;
	}

	public TargetType getType() {
		return type;
	}
	
}
