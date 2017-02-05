package scondor.deck.card.fcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FCodeReader {
	
	private static String folder="/data/code";
	public static BufferedReader fr;
	public static ArrayList<String[]> fcodes=new ArrayList<>();
	public static File[] filepaths;
	
	public static String line;
	public static String[] buffer;
	private static int filecounter=0;
	private static int counter=0;
	
	
	
	public static void load(){
		filepaths=new File(folder).listFiles();
		try {
			while(filecounter<filepaths.length&&filepaths[filecounter].isFile()){
				fr=new BufferedReader(new InputStreamReader(new FileInputStream(filepaths[filecounter])));
				fcodes.add(new String[1024]);
				while((line=fr.readLine())!=null){
					if(line.startsWith("- ")){
						fcodes.get(filecounter)[counter]=line;
					}else if(line.startsWith("> msg")){
						fcodes.get(filecounter)[counter]=line.split(" ")[1];
					}else if(line.startsWith("> var")){
						fcodes.get(filecounter)[counter]=line.substring(1, line.length());
					}else if(line.startsWith("> getEnemyAttack")){
						fcodes.get(filecounter)[counter]=line.split(" ")[1];
					}else if(line.startsWith("> getEnemyAttack")){
						fcodes.get(filecounter)[counter]=line.split(" ")[1];
					}else if(line.startsWith("> editEnemyAttack")){
						fcodes.get(filecounter)[counter]=line.split(" ")[1];
					}else if(line.startsWith("> if")){
						fcodes.get(filecounter)[counter]=line.split(" ")[1];
					}else if(line.startsWith("> jump")){
						fcodes.get(filecounter)[counter]=line.split(" ")[1];
					}else if(line.startsWith("> end")){
						fcodes.get(filecounter)[counter]=line.split(" ")[1];
						break;
					}
				}
				counter=0;
				filecounter++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public String[] getFCodeByID(int id){
			return fcodes.get(id);
	}
			
}
