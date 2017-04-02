package scondor.event;

import java.io.Serializable;

public class EffectData implements Serializable {
	
	private static final long serialVersionUID = 3521238426226552578L;
	
	private int id;
	private String name;
	private String description;
	private int fcode;
	
	public EffectData(int id, String name, String description, int fcode) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.fcode = fcode;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getFCode() {
		return fcode;
	}
	
}
