package main.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

import main.business.domain.Conference;
import main.business.domain.Paper;
import main.business.domain.Researcher;
import main.business.domain.Review;
import main.business.domain.Topic;
import main.business.domain.University;

public class Database {
	private static List<Researcher> researchers;
	private static List<Conference> conferences;
	private static List<Paper> papers;
	private static List<Review> reviews;
	private static String INIT_FILE_RESEARCHERS = "../resources/dados_researchers";

	public Database(boolean initData) {
		if (initData) {
			initData();
		}
	}

	public Database() {
		this(true);
	}

	public static void initData() {
		parsingCSVResearchers();
		conferences = null;
		papers = null;
		reviews = null;
	}

	public static List<Researcher> getResearchers() {
		return researchers;
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

	private static void parsingCSVResearchers() {
		String line;
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream is = classloader.getResourceAsStream(INIT_FILE_RESEARCHERS);
			 BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
			while((line = buffer.readLine()) != null) {
				StringTokenizer stringToken = new StringTokenizer(line, ",");

				while(stringToken.hasMoreElements()) {
					int id = Integer.parseInt(stringToken.nextToken());
					String name = 	stringToken.nextToken();
					String affiliationName = stringToken.nextToken();
					University affiliation = new University(affiliationName);
					Researcher researcher = new Researcher(name, affiliation);
					researcher.setId(id);
					String researchTopics = stringToken.nextToken();
					//Remove '[' e ']' da String
					researchTopics = researchTopics.substring(1, researchTopics.length()-1);
					StringTokenizer stringTokenTopics = new StringTokenizer(researchTopics, ",");
					while(stringTokenTopics.hasMoreElements()) {		
						researcher.addTopic(new Topic(stringToken.nextToken()));
					}
					researchers.add(researcher);
				}
			}
		} catch (Exception e) {
		
		}
	}
}
