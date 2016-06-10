package test.business.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import main.business.domain.Topic;
import main.exceptions.BusinessDomainException;

public class TopicTest {
	@Test
	public void invalidName() {
		try {
			new Topic(null);
			assertTrue(false);
		} catch (BusinessDomainException e) {
			assertTrue(true);
		}
	}
}