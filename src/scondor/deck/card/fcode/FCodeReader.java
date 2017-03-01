package scondor.deck.card.fcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FCodeReader {

	private static final int FCODESIZE = 64;

	private static String folder = "data/code/";
	public static BufferedReader fr;
	public static ArrayList<FCode> fcodes = new ArrayList<>();
	public static File[] filepaths;

	public static String line;
	public static String[] buffer;
	private static int filecounter = 0;
	private static int counter = 0;

	public static void load() {
		filepaths = new File(folder).listFiles();
		try {
			while (filecounter < filepaths.length && filepaths[filecounter].isFile()) {

				fr = new BufferedReader(new InputStreamReader(new FileInputStream(filepaths[filecounter]), "UTF-8"));
				fcodes.add(new FCode(new String[FCODESIZE], Integer.parseInt(filepaths[filecounter].getName().substring(2).split(".code")[0])));

				counter = 0;

				while ((line = fr.readLine()) != null) {
					if (line.startsWith("- ")) {
						fcodes.get(filecounter).setLines(counter, line);
					} else if (line.startsWith("> ")) {
						fcodes.get(filecounter).setLines(counter, line.substring(2));
					}
					counter++;
				}

				filecounter++;

				fr.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static FCode getFCode(int id) {
		for (FCode fcode : fcodes)
			if (fcode.getID() == id)
				return fcode;
		return null;
	}

}
