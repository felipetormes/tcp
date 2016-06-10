package test.business.domain;

import static org.junit.Assert.*;
import main.business.domain.Paper;
import main.business.domain.Researcher;
import main.business.domain.Review;
import main.data.Database;
import main.exceptions.BusinessDomainException;

import org.junit.Before;
import org.junit.Test;

public class ReviewTest {
	Database database;
	
	@Before
	public void setup() throws BusinessDomainException {
		database = buildDatabase("issue30/case1/");
	}
	
	@Test
	public void test1() throws BusinessDomainException {
		Researcher r = database.getResearcherById(1);
		Paper p = database.getPaperById(2);
		Review rev = new Review(p, r);
		
		assertTrue(rev.isPendingGrade());
		assertAttr(true, rev, 3);
		assertAttr(true, rev, 0);
		assertAttr(true, rev, -3);
		assertAttr(false, rev, 4);
		assertAttr(false, rev, -4);
	}
	
	public void assertAttr(boolean succeeds, Review r, double grade) {
		try {
			r.setGrade(grade);
			assertTrue(succeeds);
		} catch (BusinessDomainException e) {
			assertFalse(succeeds);
		}
	}
	
	private Database buildDatabase(String root) throws BusinessDomainException {
		System.out.println(">> " + root);
		String researchers = root + "pesquisadores.csv";
		String conferences = root + "conferencias.csv";
		String articles = root + "artigos.csv";
		String attributions = root + "atribuicoes.csv";

		Database database = new Database(true, researchers, conferences,
				articles, attributions);
		// System.out.println(database);
		return database;
	}
}
