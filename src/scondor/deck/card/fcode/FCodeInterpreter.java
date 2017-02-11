package scondor.deck.card.fcode;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.card.troops.TroopCardData;

public class FCodeInterpreter {
	
	private List<Variable> vars = new ArrayList<>();
	private List<Label> labels=new ArrayList<>();
	
	private int id;
	
	private String[] fcodeline;
	private int counter=0;
	private String[] bufferarray;
	private Target t;
	
	
	public FCodeInterpreter(int id, Target t) {
		
		this.id=id;
		this.t=t;
		
		fcodeline=FCodeReader.getFCodeByID(this.id);
		
	}
	
	public void triger(){
		for(counter=0;!(fcodeline[counter].isEmpty());counter++)
			//Label
			if(fcodeline[counter].startsWith("- ")){
				labels.add(new Label(fcodeline[counter].substring(2),counter));
			// Variable declaration
			}else if(fcodeline[counter].startsWith("var")){
				bufferarray=fcodeline[counter].split(" ");
				vars.add(new Variable(bufferarray[1],Integer.parseInt(bufferarray[2])));
			// Variable edit using offset value
			}else if(fcodeline[counter].startsWith("editvar")){
				bufferarray=fcodeline[counter].split("(")[1].split(")")[0].split(",");
				getVar(bufferarray[0]).editValue(Integer.parseInt(bufferarray[1]));
			//Variable edit using absolute value
			}else if(fcodeline[counter].startsWith("setvar")){
				bufferarray=fcodeline[counter].split("(")[1].split(")")[0].split(",");
				getVar(bufferarray[0]).setValue(Integer.parseInt(bufferarray[1]));
			//Jump command
			}else if(fcodeline[counter].startsWith("jump")){
				counter=getLabel(fcodeline[counter].split(" ")[1]).getValue();
			// get Attack of Enemy
			}else if(fcodeline[counter].startsWith("getEnemyAttack")){
				getVar((fcodeline[counter].split("(")[1].split(")")[0])).setValue(((TroopCardData)t.getCard()).getAttack());
			// set Attack of Enemy
			}else if(fcodeline[counter].startsWith("setEnemyAttack")){
				((TroopCardData) t.getCard()).setAttack(getVar(fcodeline[counter].split("(")[1].split(")")[0]).getValue());
			// set Attack of Enemy using offset value
			}else if(fcodeline[counter].startsWith("editEnemyAttack")){
				((TroopCardData)t.getCard()).setAttack(((TroopCardData)t.getCard()).getAttack()+(getVar(fcodeline[counter].split("(")[1].split(")")[0]).getValue()));
			//if query
			}else if(fcodeline[counter].startsWith("if")){
				bufferarray=fcodeline[counter].split("(")[1].split(")")[0].split(",")[0].split("==");
				if(getVar(bufferarray[0]).getValue()==Integer.parseInt(bufferarray[1])){
					counter=Integer.parseInt(fcodeline[counter].split("(")[1].split(")")[0].split(",")[1]);
				}
			}
	}
	
	public Variable getVar(String name) {
		for (Variable var : vars) if (var.getName().equals(name)) return var;
		return null;
	}
	public Label getLabel(String name) {
		for (Label lab : labels) if (lab.getName().equals(name)) return lab;
		return null;
	}
	
}
