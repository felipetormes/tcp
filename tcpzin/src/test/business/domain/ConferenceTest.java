package test.business.domain;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import main.business.PapersManagementService;
import main.business.impl.PapersManagementServiceImpl;
import main.data.Database;
import main.exceptions.BusinessDomainException;
import main.exceptions.BusinessServiceException;

import org.junit.Before;
import org.junit.Test;

public class ConferenceTest {
	Database database;
	PapersManagementService management;
	
	@Before
	public void setup() throws BusinessDomainException {
		database = buildDatabase("issue26/case1/");
		management = new PapersManagementServiceImpl(database);
	}
	
	@Test
	public void test1() throws BusinessServiceException, BusinessDomainException {
		Integer[] pairs = {7, 1, 8, 4, 9, 3, 10, 5, 11, 6, 7, 2, 8, 6, 9, 1, 10, 4, 11, 3};
		testAlloc("ICSE", 2, pairs);
	}
	
	@Test
	public void test2() throws BusinessServiceException, BusinessDomainException {
		Integer[] pairs = {2, 1, 3, 4, 4, 3, 5, 5, 6, 6, 2, 2, 3, 6, 4, 1, 5, 4, 6, 3};
		testAlloc("FSE", 2, pairs);
	}
	
	@Test
	public void test3() throws BusinessServiceException {
		testAllocFails("FSE", 3);
	}
	
	private void testAllocFails(String conf, int numReviewers) throws BusinessServiceException {
		try {
			testAlloc(conf, numReviewers, null);
			assertTrue(false);
		} catch (BusinessDomainException e) {
			assertTrue(true);
		}
	}
	
	private void testAlloc(String conf, int numReviewers, Integer[] pairs) throws BusinessServiceException, BusinessDomainException {
		Map<Integer, List<Integer>> rid2pids = management.allocPapersToReviewers(conf, numReviewers);
		assertAttributions(rid2pids, pairs);
	}
	
	private void assertAttributions(Map<Integer, List<Integer>> rid2pids, Integer[] pairs) {
		for (int i = 0; i < pairs.length - 1; i += 2) {
			assertTrue(rid2pids.get(pairs[i + 1]).contains(pairs[i]));
		}
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
