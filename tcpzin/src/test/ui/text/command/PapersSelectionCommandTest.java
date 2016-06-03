package test.ui.text.command;

import static org.junit.Assert.*;

import main.business.PapersManagementService;
import main.business.impl.PapersManagementServiceImpl;
import main.data.Database;
import main.exceptions.InvalidNameException;
import main.ui.text.command.PapersSelectionCommand;

import org.junit.Before;
import org.junit.Test;

public class PapersSelectionCommandTest {

	private PapersSelectionCommand paperSelection;
	private Database database;
	private PapersManagementService paperManagement;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		database = new Database();
		paperManagement = new PapersManagementServiceImpl(database);
		paperSelection = new PapersSelectionCommand(paperManagement);

	}

	@Test
	public void TestGradeAttribuiton() throws InvalidNameException {
		paperSelection.execute();

	}

}
