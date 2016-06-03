package main.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import main.business.domain.Conference;
import main.business.domain.Paper;
import main.business.domain.Researcher;
import main.business.domain.Review;

/* to build researchers: */
import main.business.domain.Topic;
import main.business.domain.University;

import main.exceptions.InvalidNameException;

public class Database {
	private static List<Researcher> researchers;
	private static List<Conference> conferences;
	private static List<Paper> papers;
	private static List<Review> reviews;
	private static String RESEARCHERS_FILE = "pesquisadores.csv";
	private static String CONFERENCES_FILE = "conferencias.csv";
	private static String ARTICLES_FILE = "artigos.csv";
	private static String ATTRIBUTIONS_FILE = "atribuicoes.csv";
	private static double NO_GRADE = -10;

	public Database(boolean initData) {
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
			papers = initArticles();
			reviews = initAttributions();
		} catch (InvalidNameException e) {
			e.printStackTrace();
		}
	}
	
	private static List<Researcher> initResearchers() throws InvalidNameException {
		List<Researcher> researchers = new ArrayList<Researcher>();
		
		List<String[]> csv_lines = readResourceCSV(RESEARCHERS_FILE);
		for (String[] fields : csv_lines) {
			int id = Integer.parseInt(fields[0]);
			String name = fields[1];
			University affiliation = new University(fields[2]);
			List<Topic> topics = new ArrayList<Topic>();
			int i = 3;
			while (i < fields.length) {
				topics.add(new Topic(fields[i++]));
			}
			
			researchers.add(new Researcher(name, affiliation, topics, id));
		}
		
		return researchers;
	}
	
	private static List<Conference> initConferences() {
		List<Conference> conferences = new ArrayList<Conference>();
		
		List<String[]> csv_lines = readResourceCSV(CONFERENCES_FILE);
		for (String[] fields : csv_lines) {
			String initials = fields[0];
			
			Conference conference = new Conference(initials);
			
			int i = 1;
			while (i < fields.length) {
				int id = Integer.parseInt(fields[i]);
				conference.addCommitteeMember(getResearcherById(id));
				i++;
			}
			
			conferences.add(conference);
		}
		
		return conferences;
	}
	
	private static List<Paper> initArticles() throws InvalidNameException {
		List<Paper> papers = new ArrayList<Paper>();
		
		List<String[]> csv_lines = readResourceCSV(ARTICLES_FILE);
		for (String[] fields : csv_lines) {
			Researcher authorPaper = null;
			int id = Integer.parseInt(fields[0]);
			String title = fields[1];
			int authorId = Integer.parseInt(fields[2]);
			List<Researcher> allResearchers = Database.researchers;
			for (Researcher author : allResearchers) {
				if (author.getId() == authorId) {
					authorPaper = author;
			}
		}
			Conference initialsConferences = new Conference(fields[3]);
			String topicName = fields[4];
			Paper paper = new Paper(id, title, authorPaper, new Topic(topicName), initialsConferences, null);
			papers.add(paper);
		}
		
		return papers;
	}
	
	private static List<Review> initAttributions() {
		List<Review> reviews = new ArrayList<Review>();
		
		List<String[]> csv_lines = readResourceCSV(ATTRIBUTIONS_FILE);
		for (String[] fields : csv_lines) {
			Researcher researcherPaper = null;
			Paper articleId = null;
			int paperId = Integer.parseInt(fields[0]);
			int reviewerId = Integer.parseInt(fields[1]);
			List<Paper> allPapers = Database.papers;
			for (Paper paper : allPapers) {
				if (paper.getId() == paperId) {
					articleId = paper;
				}
			}
			List<Researcher> allResearchers = Database.researchers;
			for (Researcher researcher : allResearchers) {
				if (researcher.getId() == reviewerId) {
					researcherPaper = researcher;
				}
			}
			double grade = 0;
			try {
				grade = Double.parseDouble(fields[2]);
			} catch (NumberFormatException e) {
				grade = NO_GRADE;
			}
			System.out.println(grade);
			Review review = new Review(articleId, researcherPaper, grade);
			reviews.add(review);
			
		}
		
		return reviews;
	}
	

	public static List<Researcher> getResearchers() {
		return researchers;
	}
	
	public static Researcher getResearcherById(int id) {
		for (Researcher researcher : researchers) {
			if (researcher.getId() == id) {
				return researcher;
			}
		}
		
		return null;
	}

	public static List<Conference> getConferences() {
		return conferences;
	}

	public static List<Paper> getPapers() {
		return papers;
	}

	public static List<Review> getReviews() {
		return reviews;
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
		for (Researcher researcher : researchers) {
			output += researcher + "\n";
		}
		
		output += "\nCONFERENCES:\n";
		
		for (Conference conference : conferences) {
			output += conference + "\n";
		}
		
		output += "\nPAPERS:\n";
		
		for (Paper paper : papers) {
			output += paper + "\n";
		}
		
		output += "\nATTRIBUTIONS:\n";
		
		for (Review review : reviews) {
			output += review + "\n";
		}
		
		return output;
	}
}
