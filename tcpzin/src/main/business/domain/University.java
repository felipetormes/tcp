package main.business.domain;

import main.exceptions.InvalidNameException;

public class University {
	private String name;
	
	public University(String name) throws InvalidNameException {
		if (name != null) {
			this.name = name;
		} else {
			throw new InvalidNameException("Invalid university name.");
		}
	}
	
	public String getName(){
		return name;
	}
}



