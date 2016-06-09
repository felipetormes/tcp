package test.business.domain;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import main.business.domain.Conference;
import main.business.domain.Paper;
import main.business.domain.Researcher;
import main.data.Database;
import main.exceptions.BusinessDomainException;

import org.junit.Before;
import org.junit.Test;

public class ConferenceTest {
	Database database;
	Map<Researcher, Integer> allocSoFar;
	
	@Before
	public void setup() throws BusinessDomainException {
		database = buildDatabase("issue26/case1/");
		allocSoFar = buildAllocSoFar();
	}
	
	@Test
	public void testChooseBestCandidate() throws BusinessDomainException {
		assertTrue(chooseBestCandidate(1) == 4);
		assertTrue(chooseBestCandidate(4) == 1);
	}
	
	public int chooseBestCandidate(int Aid) throws BusinessDomainException {
		Paper paper = database.getPaperById(Aid);
		
		Conference c = database.getConferenceByInitials("C1");
		int bestCandidateId = c.chooseBestCandidate(paper, allocSoFar).getId();
		
		return bestCandidateId;
	}
	
	private Map<Researcher, Integer> buildAllocSoFar() {
		Map<Researcher, Integer> allocSoFar = new HashMap<Researcher, Integer>();
		allocSoFar.put(database.getResearcherById(1), 1);
		allocSoFar.put(database.getResearcherById(2), 1);
		allocSoFar.put(database.getResearcherById(3), 2);
		allocSoFar.put(database.getResearcherById(4), 1);
		allocSoFar.put(database.getResearcherById(6), 2);
		
		return allocSoFar;
	}
	
	private Database buildDatabase(String root) throws BusinessDomainException {
		System.out.println(">> " + root);
		String researchers = root + "pesquisadores.csv";
		String conferences = root + "conferencias.csv";
		String articles = root + "artigos.csv";
		String attributions = root + "atribuicoes.csv";
		
		Database database = new Database(true, researchers, conferences, articles, attributions);
		//System.out.println(database);
		return database;
	}
}
