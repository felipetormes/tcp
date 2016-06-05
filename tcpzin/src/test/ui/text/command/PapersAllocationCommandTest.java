package test.ui.text.command;

import main.business.PapersManagementService;
import main.business.impl.PapersManagementServiceImpl;
import main.data.Database;
import main.ui.text.command.PapersAllocationCommand;

import org.junit.Before;
import org.junit.Test;

public class PapersAllocationCommandTest {
	private Database database;
	private PapersManagementService pms;
	private PapersAllocationCommand command;
	
	@Before
	public void setup() {
		database = new Database();
		pms = new PapersManagementServiceImpl(database);
		command = new PapersAllocationCommand(pms);
	}
	
	@Test
	public void execute() {
		command.execute();
	}
}
