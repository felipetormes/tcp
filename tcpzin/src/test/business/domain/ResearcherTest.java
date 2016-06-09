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
			assertTrue(database.getResearcherById(2).reviews(database.getPaperById(1)));
			assertFalse(database.getResearcherById(1).reviews(database.getPaperById(2)));
		} catch (BusinessDomainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Database buildDatabase(String root) throws BusinessDomainException {
		System.out.println(">> " + root);
		String researchers = root + "pesquisadores.csv";
		String conferences = root + "conferencias.csv";
		String articles = root + "artigos.csv";
		String attributions = root + "atribuicoes.csv";
		
		database = new Database(true, researchers, conferences, articles, attributions);
		//System.out.println(database);
		return database;
	}
	
	@After
	public void setdown() {
		//System.out.println(database);
	}
}
