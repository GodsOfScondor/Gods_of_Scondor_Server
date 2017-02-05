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
<<<<<<< HEAD
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
=======
			fr=new BufferedReader(new InputStreamReader(new FileInputStream(new File(fcode_file))));
			
			//no if for # cause its supposed to be ignored
			while((line=fr.readLine())!=null){
				if(line.startsWith("- ")){
					fcodes.add(new ArrayList<>());
					counter++;
					fcodes.get(counter).add(line.split(" ")[1]);
				}else if(line.startsWith("> msg")){
					fcodes.get(counter).add(line.split(" ")[1]);
				}else if(line.startsWith("> var")){
					fcodes.get(counter).add(line.substring(1, line.length()));
				}else if(line.startsWith("> getEnemyAttack")){
					fcodes.get(counter).add(line.split(" ")[1]);
				}else if(line.startsWith("> getEnemyAttack")){
					fcodes.get(counter).add(line.split(" ")[1]);
				}else if(line.startsWith("> editEnemyAttack")){
					fcodes.get(counter).add(line.split(" ")[1]);
				}else if(line.startsWith("> if")){
					fcodes.get(counter).add(line.split(" ")[1]);
>>>>>>> branch 'master' of https://github.com/GodsOfScondor/Gods_of_Scondor_Server.git
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
	
<<<<<<< HEAD
	public String[] getFCodeByID(int id){
			return fcodes.get(id);
	}
			
=======
	public ArrayList<String> getFCodeByID(int id){
		return fcodes.get(id);
	}
	
>>>>>>> branch 'master' of https://github.com/GodsOfScondor/Gods_of_Scondor_Server.git
}
