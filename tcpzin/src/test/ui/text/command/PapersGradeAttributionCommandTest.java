package test.ui.text.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.ui.text.UIUtils;
import main.ui.text.command.PapersGradesAttributionCommand;
import main.business.PapersManagementService;
import main.business.impl.PapersManagementServiceImpl;
import main.data.Database;
import main.exceptions.BusinessServiceException;
import main.exceptions.InvalidNameException;

public class PapersGradeAttributionCommandTest {

	private PapersGradesAttributionCommand paperGrade;
	private Database database;
	private PapersManagementService paperManagement;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		database = new Database();
		paperManagement = new PapersManagementServiceImpl(database);
		paperGrade = new PapersGradesAttributionCommand(paperManagement);
	}

	@Test
	public void TestGradeAttribuiton() throws InvalidNameException, BusinessServiceException {
		try {
			paperGrade.execute();
		} catch (Exception e) {
			System.out.println(UIUtils.getText(e.getMessage()));			
		}
	}
	
	@After
	public void setdown() {
		System.out.println(database);
	}
}
