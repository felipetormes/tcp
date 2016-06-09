package test.business.domain;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	Map<Researcher, List<Paper>> reviewer2papers;
	
	@Before
	public void setup() throws BusinessDomainException {
		database = buildDatabase("issue26/case1/");
		reviewer2papers = buildReviewer2papers();
	}
	
	@Test
	public void testChooseBestCandidate() throws BusinessDomainException {
		assertTrue(chooseBestCandidate(1) == 4);
		assertTrue(chooseBestCandidate(4) == 1);
	}
	
	public int chooseBestCandidate(int Aid) throws BusinessDomainException {
		Paper paper = database.getPaperById(Aid);
		
		Conference c = database.getConferenceByInitials("C1");
		int bestCandidateId = c.chooseBestCandidate(paper, reviewer2papers).getId();
		
		return bestCandidateId;
	}
	
	private Map<Researcher, List<Paper>> buildReviewer2papers() {
		Map<Researcher, List<Paper>> reviewer2papers = new HashMap<Researcher, List<Paper>>();
		for (int id = 1; id <= 6; id++) {
			reviewer2papers.put(database.getResearcherById(id), new ArrayList<Paper>());
		}
		reviewer2papers.get(database.getResearcherById(2)).add(database.getPaperById(1));
		reviewer2papers.get(database.getResearcherById(3)).add(database.getPaperById(1));
		reviewer2papers.get(database.getResearcherById(4)).add(database.getPaperById(2));
		reviewer2papers.get(database.getResearcherById(3)).add(database.getPaperById(2));
		reviewer2papers.get(database.getResearcherById(1)).add(database.getPaperById(3));
		reviewer2papers.get(database.getResearcherById(6)).add(database.getPaperById(4));
		reviewer2papers.get(database.getResearcherById(6)).add(database.getPaperById(5));
		
		return reviewer2papers;
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
