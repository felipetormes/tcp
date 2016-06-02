package test.data;

import main.data.Database;

import org.junit.Before;



public class DatabaseTest {
	Database database;

	public static void main(String[] args) {
		Database database = new Database();
		System.out.println(database);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		database = new Database();
	}
}

