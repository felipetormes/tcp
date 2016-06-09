package test.data;

import main.data.Database;

import org.junit.Before;
import org.junit.Test;

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
	public void TestDatabase() {
		System.out.println(database);

	}
}
