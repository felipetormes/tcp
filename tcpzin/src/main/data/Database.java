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
import main.exceptions.InvalidNameException;
/* to build researchers: */

public class Database {
	private static Map<Integer, Researcher> researchers;
	private static Map<String, Conference> conferences;
	private static Map<Integer, Paper> papers;
	private static String RESEARCHERS_FILE = "pesquisadores.csv";
	private static String CONFERENCES_FILE = "conferencias.csv";
	private static String ARTICLES_FILE = "artigos.csv";
	private static String ATTRIBUTIONS_FILE = "atribuicoes.csv";
	private static double NO_GRADE = -10;

	public Database(boolean initData) {
		researchers = new HashMap<Integer, Researcher>();
		conferences = new HashMap<String, Conference>();
		papers = new HashMap<Integer, Paper>();
		if (initData) {
			initData();
		}
	}

	public Database() {
		this(true);
	}

	private static void initData() {
		try {
			researchers = initResearchers();
			conferences = initConferences();
			papers = initPapers();
			initAttributions();
		} catch (InvalidNameException e) {
			e.printStackTrace();
		}
	}

	private static Map<Integer, Researcher> initResearchers() throws InvalidNameException {
		List<String[]> csv_lines = readResourceCSV(RESEARCHERS_FILE);

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

			researchers.put(id, new Researcher(name, affiliation, topics));
		}

		return researchers;
	}

	private static Map<String, Conference> initConferences() {
		List<String[]> csv_lines = readResourceCSV(CONFERENCES_FILE);

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

	private static Map<Integer, Paper> initPapers() throws InvalidNameException {
		List<String[]> csv_lines = readResourceCSV(ARTICLES_FILE);

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

	private static void initAttributions() {
		List<String[]> csv_lines = readResourceCSV(ATTRIBUTIONS_FILE);
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

	public static Researcher getResearcherById(int id) {
		return researchers.get(id);
	}

	public static Conference getConferenceByInitials(String initials) {
		return conferences.get(initials);
	}

	public static Paper getPaperById(int id) {
		return papers.get(id);
	}

	public static List<Researcher> getResearchers() {
		return new ArrayList<Researcher>(researchers.values());
	}

	public static List<Conference> getConferences() {
		return new ArrayList<Conference>(conferences.values());
	}

	public static List<Paper> getPapers() {
		return new ArrayList<Paper>(papers.values());
	}

	private static List<String[]> readResourceCSV(String filename) {
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
