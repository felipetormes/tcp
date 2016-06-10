package test.ui.text.command;

import org.junit.Before;
import org.junit.Test;

import main.ui.ConferenceUI;
import main.ui.text.ConferenceTextInterface;
import main.business.PapersManagementService;
import main.business.impl.PapersManagementServiceImpl;
import main.data.Database;

public class ConferenceUITest {

	private ConferenceUI conferenceUI;
	private Database database;
	private PapersManagementService paperManagement;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		database = new Database();
		paperManagement = new PapersManagementServiceImpl(database);
		conferenceUI = new ConferenceTextInterface(paperManagement);
	}

	@Test
	public void TestUI()  {
		conferenceUI.showUI();

	}
}
