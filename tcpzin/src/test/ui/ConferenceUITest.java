package test.ui;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import com.sun.scenario.Settings;

import main.ui.ConferenceUI;
import main.ui.text.ConferenceTextInterface;


	public class ConferenceUITest {
		
		ConferenceUI conferenceUI;

		/**
		 * @throws java.lang.Exception
		 */
		@Before
		public void setUp() throws Exception {
			conferenceUI = new ConferenceTextInterface();
		}
		
		
		@Test
		public void addCommand() {
			
		}
	}

