package scondor.deck.card.fcode;

public class Label {
	
	private int value;
	private String name;
	
	protected Label(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	protected String getName() {
		return name;
	}
	
	protected int getValue() {
		return value;
	}
	
}
