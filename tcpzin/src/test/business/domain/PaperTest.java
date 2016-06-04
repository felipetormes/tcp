package test.business.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.business.domain.Paper;
import main.business.domain.Review;

import org.junit.Before;
import org.junit.Test;

public class PaperTest {
	List<Paper> papers = new ArrayList<Paper>();
	List<Review> reviews= new ArrayList<Review>();

	@Before
	public void setup() {
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
}
