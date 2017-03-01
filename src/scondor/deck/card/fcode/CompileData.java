package scondor.deck.card.fcode;

import scondor.session.GamePlayer;

public class CompileData {
	
	private GamePlayer player;
	private GamePlayer enemy;
	private int target;
	private TargetType type;
	
	public CompileData(GamePlayer player, GamePlayer enemy, int target, TargetType type) {
		this.player = player;
		this.enemy = enemy;
		this.target = target;
		this.type = type;
	}

	public GamePlayer getPlayer() {
		return player;
	}

	public GamePlayer getEnemy() {
		return enemy;
	}

	public int getTarget() {
		return target;
	}

	public TargetType getType() {
		return type;
	}
	
}
