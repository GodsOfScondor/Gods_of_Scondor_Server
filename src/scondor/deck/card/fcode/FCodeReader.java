package scondor.deck.card.fcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FCodeReader {
	
	private static final int FCODESIZE=64;
	
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
				fcodes.add(new String[FCODESIZE]);
				while((line=fr.readLine())!=null){
					if(line.startsWith("- ")){
						fcodes.get(filecounter)[counter]=line;
					}else if(line.startsWith("> ")){
						fcodes.get(filecounter)[counter]=line.substring(2);
					}
					counter++;
				}
				counter=0;
				filecounter++;
				fr.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static String[] getFCodeByID(int id){
			return fcodes.get(id);
	}
			
}
