package main.business.domain;

import main.exceptions.*;

public class Topic {
	private String name;

	public Topic(String name) throws BusinessDomainException {
		if (name != null) {
			this.name = name;
		} else {
			throw new BusinessDomainException(
					"exception.business.domain.invalidTopic");
		}
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Topic) {
			Topic other = (Topic) obj;
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
