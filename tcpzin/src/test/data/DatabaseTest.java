package test.data;

import java.util.List;

import main.business.domain.Conference;
import main.business.domain.Researcher;
import main.data.Database;

import org.junit.Before;



public class DatabaseTest {
	Database database;

	public static void main(String[] args) {
		Database database = new Database();
		List<Researcher> researchers = database.getResearchers();
		List<Conference> conferences = database.getConferences();
		
		for (Researcher researcher : researchers) {
			System.out.println(researcher);
		}
		
		for (Conference conference : conferences) {
			System.out.println(conference);
		}
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		database = new Database();
	}
}

