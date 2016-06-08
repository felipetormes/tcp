package main.business.domain;

import main.exceptions.BusinessDomainException;
import main.exceptions.InvalidNameException;

public class University {
	private String name;
	
	public University(String name) throws BusinessDomainException {
		if (name != null) {
			this.name = name;
		} else {
			throw new BusinessDomainException("exception.business.domain.invalidUniversity");
		}
	}
	
	public String getName(){
		return name;
	}
	
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof University) {
			University other = (University) obj;
			return this.getName().equals(other.getName());
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
}




