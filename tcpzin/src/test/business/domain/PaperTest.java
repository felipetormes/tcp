package test.business.domain;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.business.domain.Paper;
import main.data.Database;
import main.exceptions.BusinessDomainException;

import org.junit.Before;
import org.junit.Test;

public class PaperTest {
	List<Paper> papers;
	Database database;

	@Before
	public void setup() throws BusinessDomainException {
		database = buildDatabase("issue28/case1/");
		papers = database.getPapersList();
	}

	public void testSetGrade() {
		assertFalse(setGrade(-5, 2, 1));
		assertFalse(setGrade(5, 2, 1));
		assertFalse(setGrade(-5, 4, 4));
	}

	private boolean setGrade(int grade, int Rid, int Aid) {
		try {
			database.getPaperById(Aid).setGrade(
					database.getResearcherById(Rid), grade);
			return true;
		} catch (BusinessDomainException e) {
			return false;
		}
	}

	@Test
	public void testGetAverageGrade() {
		assertTrue(closeEnough(database.getPaperById(1).getAverageGrade(), 1.));
		assertTrue(closeEnough(database.getPaperById(2).getAverageGrade(), 2.));
		assertTrue(closeEnough(database.getPaperById(3).getAverageGrade(), 3.));
		assertTrue(closeEnough(database.getPaperById(4).getAverageGrade(), 3.));
	}

	private boolean closeEnough(Double a, Double b) {
		double eps = 0.0001; /* floating point difference tolerance */
		return Math.abs(a - b) < eps;
	}

	@Test
	public void testHasPendingReviews() throws BusinessDomainException {
		database = buildDatabase("issue28/case2/");
		assertTrue(database.getPaperById(7).hasPendingReviews());
		assertTrue(database.getPaperById(6).hasPendingReviews());
		assertFalse(database.getPaperById(1).hasPendingReviews());
	}

	@Test
	public void testComparatorAsc() {
		papers = Paper.sortPaperByGrade(papers, true);

		Double previousGrade = papers.get(0).getAverageGrade();
		for (int i = 1; i < papers.size(); i++) {
			Double currentGrade = papers.get(i).getAverageGrade();
			assertTrue(previousGrade <= currentGrade);
			previousGrade = currentGrade;
		}
	}

	@Test
	public void testComparatorDes() {
		papers = Paper.sortPaperByGrade(papers, false);

		Double previousGrade = papers.get(0).getAverageGrade();
		for (int i = 1; i < papers.size(); i++) {
			Double currentGrade = papers.get(i).getAverageGrade();
			assertTrue(previousGrade >= currentGrade);
			previousGrade = currentGrade;
		}
	}

	@Test
	public void testHash() {
		Map<Paper, Integer> paper2id = new HashMap<Paper, Integer>();
		for (Paper paper : papers) {
			paper2id.put(paper, paper.getId());
		}

		for (Paper paper : papers) {
			assertTrue(paper2id.get(paper) == paper.getId());
		}
	}

	@Test
	public void testOrdering() {
		Paper prev = papers.get(0);
		for (int i = 1; i < papers.size(); i++) {
			Paper curr = papers.get(i);
			boolean ordered = (prev.compareTo(curr) > 0 && prev.getId() > curr
					.getId())
					|| (prev.compareTo(curr) < 0 && prev.getId() < curr.getId());

			assertTrue(ordered);
			prev = curr;
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
