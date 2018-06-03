package com.realscores.obj;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FairwayHitEnum {
	
	YES   (0, "Yes"),
	LEFT  (1, "Left"),
	RIGHT (2, "Right"),
	SHORT (3, "Short"),
	LONG  (4, "Long");	
	
	private int id;
	private String description;
	
	private FairwayHitEnum(int id, String description){
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
