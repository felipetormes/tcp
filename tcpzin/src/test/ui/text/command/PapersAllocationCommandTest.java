package test.ui.text.command;

import main.business.PapersManagementService;
import main.business.impl.PapersManagementServiceImpl;
import main.data.Database;
import main.exceptions.BusinessServiceException;
import main.exceptions.InvalidNameException;
import main.ui.text.command.PapersAllocationCommand;
import org.junit.Before;
import org.junit.Test;

public class PapersAllocationCommandTest {
	private Database database;
	private PapersAllocationCommand paperAllocation;
	private PapersManagementService paperManagement;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		database = new Database(true,"emptyResearchers.csv","conferencias.csv","artigos.csv","atribuicoes.csv");
		paperManagement = new PapersManagementServiceImpl(database);
		paperAllocation = new PapersAllocationCommand(paperManagement);
	}

	@Test
	public void TestGradeAttribuiton() throws InvalidNameException, BusinessServiceException {
		paperAllocation.execute();
	}	
}
