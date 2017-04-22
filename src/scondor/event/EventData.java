package scondor.event;

import java.io.Serializable;

public class EventData implements Serializable {
	
	private static final long serialVersionUID = 3521238426226552578L;
	
	private String name;
	private String description;
	private int fcode;
	
	public EventData(String name, String description, int fcode) {
		this.name = name;
		this.description = description;
		this.fcode = fcode;
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
