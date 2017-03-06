package scondor.deck.card.fcode;

public class Vars {
	
	private FCode fcode;
	
	public Vars(FCode fcode) {
		this.fcode = fcode;
	}
	
	public Variable get(String name) {
		return fcode.getVar(name);
	}
	
}
