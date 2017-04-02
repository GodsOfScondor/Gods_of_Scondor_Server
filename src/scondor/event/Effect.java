package scondor.event;

import scondor.deck.card.Compilable;
import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.FCode;
import scondor.deck.card.fcode.FCodeLoader;
import scondor.deck.card.fcode.Vars;

public class Effect implements Compilable {
	
	private FCode fcode;
	private EffectData data;
	private boolean remove;
	
	public Effect(EffectData data) {
		this.data = data;
		this.fcode = FCodeLoader.getFCode(data.getFCode());
		this.remove = false;
	}
	
	public boolean trigger(CompileData data) {
		fcode.execute(data, this);
		return remove;
	}
	
	public EffectData getData() {
		return data;
	}
	
	@Override
	public void compile(Vars vars, String cmd, CompileData data) {
		
	}
	
}
