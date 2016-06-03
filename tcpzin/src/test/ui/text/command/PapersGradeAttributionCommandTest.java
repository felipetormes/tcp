package test.ui.text.command;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


import main.ui.text.command.PapersGradesAttributionCommand;
import main.business.PapersManagementService;
import main.business.impl.PapersManagementServiceImpl;
import main.data.Database;

	public class PapersGradeAttributionCommandTest {
		
		PapersGradesAttributionCommand paperGrade;
		
		/**
		 * @throws java.lang.Exception
		 */
		@Before
		public void setUp() throws Exception {
			Database database = new Database();
			PapersManagementService paperManagement = new PapersManagementServiceImpl(database);
			paperGrade = new PapersGradesAttributionCommand(paperManagement);
		}
		
		
		@Test
		public void TestGradeAttribuiton() {
			paperGrade.execute();
			
		}
	}
