package scondor.deck.card.fcode;

public class Variable {
	
	private int value;
	private String name;
	
	protected Variable(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	protected String getName() {
		return name;
	}
	
	protected int getValue() {
		return value;
	}
	
	protected void editValue(int offset){
		value=value+offset;
	}
	
	protected void setValue(int value){
		this.value=value;
	}
	
}
