package scondor.deck.card.fcode;

public class Variable {
	
	private int value;
	private String name;
	
	public Variable(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public int getValue() {
		return value;
	}
	
	public void editValue(int offset){
		value=value+offset;
	}
	
	public void setValue(int value){
		this.value=value;
	}
	
}
