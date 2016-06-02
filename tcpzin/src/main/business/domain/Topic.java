package main.business.domain;

import main.exceptions.*;

public class Topic {
	private String name;
	
	public Topic(String name) throws InvalidNameException {
		if (name != null) {
			this.name = name;
		} else {
			throw new InvalidNameException("Invalid topic name.");
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
}
