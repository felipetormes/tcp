package test.business.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.exceptions.*;
import main.business.domain.Topic;

public class TopicTest {
	@Test
	public void NullName() {
		boolean works = false;
		try {
			new Topic(null);
			works = true;
		} catch (InvalidNameException e) {
			assertFalse(works);
		}
	}
}
