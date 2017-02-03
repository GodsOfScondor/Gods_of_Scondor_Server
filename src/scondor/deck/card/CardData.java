package scondor.deck.card;

public abstract class CardData {
	
	private int mana_cost;

	public CardData(int mana_cost){
		
		
		
		this.mana_cost = mana_cost;
	}
	
	public int getManaCost(){
		return mana_cost;
	}

	public void setManaCost(int mana_cost){
		this.mana_cost = mana_cost;
	}
	
}
