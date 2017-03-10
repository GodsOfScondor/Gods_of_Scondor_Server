package scondor.player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Cards {
	
	private List<Integer> cards = new ArrayList<>();
	
	private BufferedReader reader;
	private BufferedWriter writer;
	private File file;
	private String line;

	
	public Cards(int id) {
		
		file = new File("data/cards/C"+id+".cards");
		
		load();
		
		System.out.println(cards.size());
		
	}
	
	public void reload() {
		save();
		load();
	}
	
	protected void save() {
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			
			for (Integer id : cards) {
				writer.write(id+",");
			}
			writer.write("END");
			
			writer.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void load() {
		
		if (file.exists()) {
			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				
				cards.clear();
				
				while ((line = reader.readLine()) != null) {
					if (!line.isEmpty()) {
						for (String id : line.split(",")) {
							if (!id.startsWith("END")) cards.add(Integer.parseInt(id));
						}
					}
				}
				
				
				reader.close();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			save();
		}
	}

	public void add(int id) {
		cards.add(id);
		reload();
	}
	
	public void remove(int id) {
		cards.remove(id);
		reload();
	}
	
	public boolean hasCard(int id) {
		return cards.contains(id);
	}
	
	public List<Integer> getCards() {
		return cards;
	}
	
}
