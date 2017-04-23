package scondor.deck.card.fcode;

import java.util.ArrayList;
import java.util.List;


public class FCode {

	private List<Variable> vars = new ArrayList<>();
	private List<Label> labels = new ArrayList<>();
	private String[] lines;
	private String[] buffer=new String[5];
	private int counter = 0;
	private int id;
	private Vars variablehandler;

	public FCode(String[] lines, int id) {
		this.id = id;
		this.lines = lines;
		this.variablehandler = new Vars(this);
	}

	public <DATA extends CompileData> void execute(DATA data, Compilable<DATA> compiler) {
		
		System.out.println("fcode " + id  + " is triggered!");
		
		for (counter = 0;lines[counter]!=null; counter++) {
				/*
				 *  Label
				 */
			if (lines[counter].startsWith("- ")) {
				labels.add(new Label(lines[counter].substring(2), counter));
			} 
				/*
				 *  Variable declaration
				 */
			else if (lines[counter].startsWith("var")) {
				buffer = lines[counter].split(" ");
				vars.add(new Variable(buffer[1], Integer.parseInt(buffer[2])));
			} 
				/*
				 * Variable edit using offset value
				 */
			else if (lines[counter].startsWith("editvar")) {
				buffer = lines[counter].substring(8, lines[counter].length()-1).split(",");
				getVar(buffer[0]).editValue(Integer.parseInt(buffer[1]));
				
			}
				/*
				 *  Variable edit using absolute value
				 */
			else if (lines[counter].startsWith("setvar")) {
				buffer = lines[counter].substring(7, lines[counter].length()-1).split(",");
				getVar(buffer[0]).setValue(Integer.parseInt(buffer[1]));
				
			}
				/*
				 *  Jump command
				 */
				else if (lines[counter].startsWith("jump")) {
				counter = getLabel(lines[counter].split(" ")[1]).getValue();
				
			}
				/*
				 * msg 
				 */
			else if(lines[counter].startsWith("msg")){
				System.out.println(lines[counter].substring(5, lines[counter].length()-2));
			}
				/*
				 * if query
				 */
			else if (lines[counter].startsWith("if")) {
				buffer = lines[counter].substring(3, lines[counter].length()-1).split(",")[0].split("==");
				if (getVar(buffer[0]).getValue() == Integer.parseInt(buffer[1])) {
					counter = getLabel(lines[counter].substring(3, lines[counter].length()-1).split(",")[1]).getValue();
				}
			} else {
				
				System.out.println("compiling unknown cmd!");
				
				compiler.compile(variablehandler, lines[counter], data);
				
			}
		}
	}

	protected Variable getVar(String name) {
		for (Variable var : vars)
			if (var.getName().equalsIgnoreCase(name))
				return var;
		return null;
	}

	protected Label getLabel(String name) {
		for (Label lab : labels)
			if (lab.getName().equalsIgnoreCase(name))
				return lab;
		return null;
	}
	
	public int getID() {
		return id;
	}
	
	public String getLines(int pos){
		return lines[pos];
	}
	
	public void setLines(int pos,String line){
		lines[pos]=line;
	}
	
}
