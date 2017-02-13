package scondor.deck.card;

import scondor.deck.card.fcode.FCodeInterpreter;
import scondor.deck.card.fcode.Target;
import scondor.mana.ManaType;

public abstract class Card implements Compilable {
	
	private int mana_cost;
	private ManaType mana_type;
	private FCodeInterpreter fcip;
	
	public Card(int mana_cost,ManaType mana_type,int fcode){
		this.mana_type = mana_type;
		this.mana_cost = mana_cost;
		fcip=new FCodeInterpreter(fcode);
	}

	public int getManaCost(){
		return mana_cost;
	}

	public void setManaCost(int mana_cost){
		this.mana_cost = mana_cost;
	}

	public ManaType getManatype() {
		return mana_type;
	}

	public void setManatype(ManaType manatype) {
		this.mana_type = manatype;
	}
	
	public void triggerFCode(Target t){
		fcip.triger(t);
	}
	
}
