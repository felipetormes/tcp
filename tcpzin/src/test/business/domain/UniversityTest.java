package test.business.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import main.business.domain.University;
import main.exceptions.BusinessDomainException;

public class UniversityTest {
	@Test
	public void invalidName() {
		try {
			new University(null);
			assertTrue(false);
		} catch (BusinessDomainException e) {
			assertTrue(true);
		}
	}
}
