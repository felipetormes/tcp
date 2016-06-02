package test.data;

import static org.junit.Assert.*;

import java.util.List;


import org.junit.Before;
import org.junit.Test;

import main.data.Database;



public class DatabaseTest {
		Database database;
		
		public static void main(String[] args) {
			Database database = new Database();
		}
		
		/**
		 * @throws java.lang.Exception
		 */
		@Before
		public void setUp() throws Exception {
			database = new Database();
		}
	}

