package main.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.business.domain.Conference;
import main.business.domain.Paper;
import main.business.domain.Researcher;
import main.business.domain.Review;
import main.business.domain.Topic;
import main.business.domain.University;
import main.exceptions.BusinessDomainException;
import main.exceptions.InvalidNameException;
/* to build researchers: */

public class Database {
	private Map<Integer, Researcher> researchers;
	private Map<String, Conference> conferences;
	private Map<Integer, Paper> papers;
	private final static String RESEARCHERS_INIT_FILE = "pesquisadores.csv";
	private final static String CONFERENCES_INIT_FILE = "conferencias.csv";
	private final static String ARTICLES_INIT_FILE = "artigos.csv";
	private final static String ATTRIBUTIONS_INIT_FILE = "atribuicoes.csv";
	public Database(boolean initData,
			        String researchersFile,
			        String conferencesFile,
			        String articlesFile,
			        String attributionsFile) throws BusinessDomainException {
		researchers = new HashMap<Integer, Researcher>();
		conferences = new HashMap<String, Conference>();
		papers = new HashMap<Integer, Paper>();
		if (initData) {
			initData(researchersFile,
			         conferencesFile,
			         articlesFile,
			         attributionsFile);
		}
	}
	
	public Database(boolean initData) throws BusinessDomainException {
		this(initData, RESEARCHERS_INIT_FILE , CONFERENCES_INIT_FILE, ARTICLES_INIT_FILE, ATTRIBUTIONS_INIT_FILE );
	}

	public Database() throws BusinessDomainException {
		this(true);
	}

	private void initData(String researchersFile,
	                             String conferencesFile,
	                             String articlesFile,
	                             String attributionsFile) throws BusinessDomainException {
		try {
			researchers = initResearchers(researchersFile);
			conferences = initConferences(conferencesFile);
			papers = initPapers(articlesFile);
			initAttributions(attributionsFile);
		} catch (InvalidNameException e) {
			e.printStackTrace();
		}
	}

	public Researcher getResearcherById(int id) {
		return researchers.get(id);
	}

	public Conference getConferenceByInitials(String initials) {
		return conferences.get(initials);
	}

	public Paper getPaperById(int id) {
		return papers.get(id);
	}

	public Map<Integer, Researcher> getResearchers() {
		return researchers;
	}
	
	public List<Researcher> getResearchersList() {
		return new ArrayList<Researcher>(researchers.values());
	}

	public Map<String, Conference> getConferences() {
		return conferences;
	}
	
	public List<Conference> getConferencesList() {
		return new ArrayList<Conference>(conferences.values());
	}
	
	public Map<Integer, Paper> getPapers() {
		return papers;
	}

	public List<Paper> getPapersList() {
		return new ArrayList<Paper>(papers.values());
	}

	private List<String[]> readResourceCSV(String filename) {
		List<String[]> lines = new ArrayList<String[]>();

		try {
			File fin = new File(System.getProperty("user.dir") + "/src/main/resources/" + filename);
			FileInputStream fis;
			fis = new FileInputStream(fin);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));

			String line = null;
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(",", -1); /* -1 to not ignore empty fields */
				lines.add(fields);
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}

	private Map<Integer, Researcher> initResearchers(String researchersFile) throws InvalidNameException, BusinessDomainException {
		List<String[]> csv_lines = readResourceCSV(researchersFile);

		Map<Integer, Researcher> researchers = new HashMap<Integer, Researcher>();
		for (String[] fields : csv_lines) {
			Integer id = Integer.parseInt(fields[0]);
			String name = fields[1];
			University affiliation = new University(fields[2]);
			List<Topic> topics = new ArrayList<Topic>();
			int i = 3;
			while (i < fields.length) {
				topics.add(new Topic(fields[i++]));
			}

			researchers.put(id, new Researcher(name, affiliation, topics, id));
		}

		return researchers;
	}

	private Map<String, Conference> initConferences(String conferencesFile) {
		List<String[]> csv_lines = readResourceCSV(conferencesFile);

		Map<String, Conference> conferences = new HashMap<String, Conference>();
		for (String[] fields : csv_lines) {
			String initials = fields[0];
			Conference conference = new Conference(initials);
			int i = 1;
			while (i < fields.length) {
				int id = Integer.parseInt(fields[i++]);
				conference.addCommitteeMember(getResearcherById(id));
			}

			conferences.put(conference.getInitials(), conference);
		}

		return conferences;
	}

	private Map<Integer, Paper> initPapers(String articlesFile) throws InvalidNameException, BusinessDomainException {
		List<String[]> csv_lines = readResourceCSV(articlesFile);

		Map<Integer, Paper> papers = new HashMap<Integer, Paper>();
		for (String[] fields : csv_lines) {
			int id = Integer.parseInt(fields[0]);
			String title = fields[1];
			int authorId = Integer.parseInt(fields[2]);
			String conferenceInitials = fields[3];
			String topicName = fields[4];

			Conference conference = getConferenceByInitials(conferenceInitials);
			Researcher author = getResearcherById(authorId);

			Paper paper = new Paper(id, title, author, new Topic(topicName), conference, new ArrayList<Review>());
			papers.put(paper.getId(), paper);
		}

		return papers;
	}

	private void initAttributions(String attributionsFile) {
		List<String[]> csv_lines = readResourceCSV(attributionsFile);
		for (String[] fields : csv_lines) {
			Integer paperId = Integer.parseInt(fields[0]);
			Integer reviewerId = Integer.parseInt(fields[1]);

			Paper paper = getPaperById(paperId);
			Researcher reviewer = getResearcherById(reviewerId);

			if (fields[2].equals("")) {
				new Review(paper, reviewer); /* automatically associated to paper in constructor */
			} else {
				new Review(paper, reviewer, Double.parseDouble(fields[2]));
			}
		}
	}

	public String toString() {
		String output = "\nRESEARCHERS\n";
		for (Map.Entry<Integer, Researcher> entry : researchers.entrySet()) {
			output += entry.getValue() + "\n";
		}

		output += "\nCONFERENCES:\n";
		for (Map.Entry<String, Conference> entry : conferences.entrySet()) {
			output += entry.getValue() + "\n";
		}

		output += "\nPAPERS:\n";
		for (Map.Entry<Integer, Paper> entry : papers.entrySet()) {
			output += entry.getValue() + "\n";
		}

		return output;
	}
}
