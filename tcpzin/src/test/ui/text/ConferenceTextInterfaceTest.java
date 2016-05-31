package test.ui.text;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import main.ui.text.ConferenceTextInterface;


	public class ConferenceTextInterfaceTest {
		
		ConferenceTextInterface conferenceTextInterface;

		/**
		 * @throws java.lang.Exception
		 */
		@Before
		public void setUp() throws Exception {
			conferenceTextInterface = new ConferenceTextInterface();
		}
		
		
		@Test
		public void TestMenu() {
			System.out.println(conferenceTextInterface.showMenu());
			
		}
	}

