package test.business.domain;

import static org.junit.Assert.*;

import main.business.domain.University;
import main.exceptions.InvalidNameException;

import org.junit.Before;
import org.junit.Test;

	public class UniversityTest {
		@Test
		public void NullName() {
			boolean works = false;
			try {
				new University(null);
				works = true;
			} catch (InvalidNameException e) {
				assertFalse(works);
			}
		}
	}

