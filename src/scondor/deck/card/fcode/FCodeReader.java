package scondor.deck.card.fcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FCodeReader {
	
	public static final String fcode_file="data/code/fcode";
	public static BufferedReader fr;
	
	public static ArrayList<ArrayList<String>> fcodes=new ArrayList<>();
	
	public static String line;
	public static String[] buffer;
	private static int counter=-1;
	
	
	
	public static void load(){
		try {
			fr=new BufferedReader(new InputStreamReader(new FileInputStream(new File(fcode_file))));
			
			//no if for # cause its supposed to be ignored
			while((line=fr.readLine())!=null){
				if(line.startsWith("- ")){
					fcodes.add(new ArrayList<>());
					counter++;
					fcodes.get(counter).add(line.split(" ")[1]);
				}else if(line.startsWith("> getEnemyAttack")){
					fcodes.get(counter).add(line.split(" ")[1]);
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	
}
