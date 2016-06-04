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
			grade += (i*10)%7;
			papers.add(new Paper(null, null, null));
			reviews.add(new Review(papers.get(i), null, grade));
		}
	}
	
	@Test
	public void testComparatorAsc() {
		Collections.sort(papers, Paper.ascendingGradeComparator);
		
		Double prev = papers.get(0).getAverageGrade();
		for (int i = 1; i < papers.size(); i++) {
			Double curr = papers.get(i).getAverageGrade();
			assertTrue(prev <= curr);
			prev = curr;
		}
	}
	
	@Test
	public void testComparatorDes() {
		Collections.sort(papers, Paper.descendingGradeComparator);
		
		Double prev = papers.get(0).getAverageGrade();
		for (int i = 1; i < papers.size(); i++) {
			Double curr = papers.get(i).getAverageGrade();
			assertTrue(prev >= curr);
			prev = curr;
		}
	}
}
