package test.business.domain;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.business.domain.Paper;
import main.business.domain.Review;
import main.exceptions.BusinessDomainException;

import org.junit.Before;
import org.junit.Test;

public class PaperTest {
	List<Paper> papers;
	List<Review> reviews;

	@Before
	public void setup() throws BusinessDomainException {
		papers = new ArrayList<Paper>();
		reviews = new ArrayList<Review>();

		double grade = 0;
		for (int i = 0; i < 20; i++) {
			/* everything is null so I don't have to create a researcher,
			 * topic, and conference for each paper and review. could/should
			 * be added later for a complete test.
			 */
			grade += (i*10)%7; /* not so random grade */
			papers.add(i, new Paper(null, null, null));
			reviews.add(i, new Review(papers.get(i), null, grade));
		}

		for (Paper paper : papers) {
			System.out.println(paper.getId());
		}
	}

	@Test
	public void testComparatorAsc() {
		Collections.sort(papers, Paper.ascendingGradeComparator);

		Double previousGrade = papers.get(0).getAverageGrade();
		for (int i = 1; i < papers.size(); i++) {
			Double currentGrade = papers.get(i).getAverageGrade();
			assertTrue(previousGrade <= currentGrade);
			previousGrade = currentGrade;
		}
	}

	@Test
	public void testComparatorDes() {
		Collections.sort(papers, Paper.descendingGradeComparator);

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
			boolean ordered = (prev.compareTo(curr) > 0 && prev.getId() > curr.getId()) ||
			                  (prev.compareTo(curr) < 0 && prev.getId() < curr.getId());

			assertTrue(ordered);
			prev = curr;
		}
	}
}
