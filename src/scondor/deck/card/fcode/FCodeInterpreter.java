package scondor.deck.card.fcode;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.card.Card;
import scondor.deck.card.troops.TroopCardData;

public class FCodeInterpreter {
	
	private List<Variable> vars = new ArrayList<>();
	private List<Label> labels=new ArrayList<>();
	
	private int id;
	private Card enemycard;
	
	private String[] fcodeline;
	private int counter=0;
	private String[] bufferarray;
	
	
	public FCodeInterpreter(int id, TroopCardData enemycard) {
		
		this.id=id;
		this.enemycard=enemycard;
		
		fcodeline=FCodeReader.getFCodeByID(this.id);
		
		for(counter=0;!(fcodeline[counter].isEmpty());counter++)
			if(fcodeline[counter].startsWith("- ")){
				labels.add(new Label(fcodeline[counter].substring(2),counter));
			}else if(fcodeline[counter].startsWith("var")){
				bufferarray=fcodeline[counter].split(" ");
				vars.add(new Variable(bufferarray[1],Integer.parseInt(bufferarray[2])));
			}else if(fcodeline[counter].startsWith("editvar")){
				bufferarray=fcodeline[counter].split("(")[1].split(")")[0].split(",");
				getVar(bufferarray[0]).editValue(Integer.parseInt(bufferarray[1]));
			}else if(fcodeline[counter].startsWith("setvar")){
				bufferarray=fcodeline[counter].split("(")[1].split(")")[0].split(",");
				getVar(bufferarray[0]).setValue(Integer.parseInt(bufferarray[1]));
			}else if(fcodeline[counter].startsWith("jump")){
				counter=getLabel(fcodeline[counter].split(" ")[1]).getValue();
			}else if(fcodeline[counter].startsWith("getEnemyAttack")){
				getVar(fcodeline[counter].split("(")[1].split(")")[0]).setValue(enemycard.getAttack());
			}else if(fcodeline[counter].startsWith("setEnemyAttack")){
				enemycard.setAttack(getVar(fcodeline[counter].split("(")[1].split(")")[0]).getValue());
			}else if(fcodeline[counter].startsWith("editEnemyAttack")){
				enemycard.setAttack(enemycard.getAttack()+(getVar(fcodeline[counter].split("(")[1].split(")")[0]).getValue()));
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
