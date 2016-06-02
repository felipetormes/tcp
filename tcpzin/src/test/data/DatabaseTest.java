package test.data;

import static org.junit.Assert.*;

import java.util.List;


import org.junit.Before;
import org.junit.Test;

import main.data.Database;



	public class DatabaseTest {
		
		Database database;

		/**
		 * @throws java.lang.Exception
		 */
		@Before
		public void setUp() throws Exception {
			database = new Database();
		}
		
		
		@Test
		public void TestInitResercherData() {
			System.out.println(database.getResearchers());
		}
	}

