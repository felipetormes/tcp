package test.business.domain;

import static org.junit.Assert.*;

import main.data.Database;
import main.exceptions.BusinessDomainException;

import org.junit.After;
import org.junit.Test;

public class ResearcherTest {
	private Database database;

	@Test
	public void test1() {
		String root = "issue29/case1/";
		try {
			database = buildDatabase(root);
			assertSuccAtrr(2, 4);
			assertSuccAtrr(4, 2);
			assertFailedAtrr(1, 1);
			assertFailedAtrr(1, 4);
			assertFailedAtrr(5, 1);
			assertFailedAtrr(1, 2);
		} catch (BusinessDomainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void assertFailedAtrr(int Aid, int Rid) {
		assertFalse(Aid + "," + Rid, database.getResearcherById(Rid)
				.isSuitedToReview(database.getPaperById(Aid)));
	}

	private void assertSuccAtrr(int Aid, int Rid) {
		assertTrue(Aid + "," + Rid, database.getResearcherById(Rid)
				.isSuitedToReview(database.getPaperById(Aid)));
	}

	private Database buildDatabase(String root) throws BusinessDomainException {
		System.out.println(">> " + root);
		String researchers = root + "pesquisadores.csv";
		String conferences = root + "conferencias.csv";
		String articles = root + "artigos.csv";
		String attributions = root + "atribuicoes.csv";

		database = new Database(true, researchers, conferences, articles,
				attributions);
		// System.out.println(database);
		return database;
	}

	@After
	public void setdown() {
		// System.out.println(database);
	}
}
