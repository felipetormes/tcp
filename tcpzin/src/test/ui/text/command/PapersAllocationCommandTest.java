package test.ui.text.command;

import main.business.PapersManagementService;
import main.business.impl.PapersManagementServiceImpl;
import main.data.Database;
import main.exceptions.BusinessDomainException;
import main.ui.text.UIUtils;
import main.ui.text.command.PapersAllocationCommand;

import org.junit.After;
import org.junit.Test;

public class PapersAllocationCommandTest {
	private Database database;
	private PapersAllocationCommand paperAllocation;
	private PapersManagementService paperManagement;

	@Test
	public void runTests() {
		test1();
	}

	public void test1() {
		String root = "allocTestCases/case1/";

		try {
			paperAllocation = buildCommand(root);
			paperAllocation.execute();
		} catch (Exception e) {
			System.out.println(UIUtils.getText(e.getMessage()));
		}
	}

	public void test2() {
		String root = "allocTestCases/case2/";

		try {
			paperAllocation = buildCommand(root);
			paperAllocation.execute();
		} catch (Exception e) {
			System.out.println(UIUtils.getText(e.getMessage()));
		}
	}

	public void test3() {
		String root = "allocTestCases/case3/";

		try {
			paperAllocation = buildCommand(root);
			paperAllocation.execute();
		} catch (Exception e) {
			System.out.println(UIUtils.getText(e.getMessage()));
		}
	}

	public void test4() {
		String root = "allocTestCases/case4/";

		try {
			paperAllocation = buildCommand(root);
			paperAllocation.execute();
		} catch (Exception e) {
			System.out.println(UIUtils.getText(e.getMessage()));
		}
	}

	private PapersAllocationCommand buildCommand(String root)
			throws BusinessDomainException {
		System.out.println(">> " + root);
		String researchers = root + "pesquisadores.csv";
		String conferences = root + "conferencias.csv";
		String articles = root + "artigos.csv";
		String attributions = root + "atribuicoes.csv";

		database = new Database(true, researchers, conferences, articles,
				attributions);
		System.out.println(database);
		paperManagement = new PapersManagementServiceImpl(database);
		return new PapersAllocationCommand(paperManagement);
	}

	@After
	public void setdown() {
		System.out.println(database);
	}
}
