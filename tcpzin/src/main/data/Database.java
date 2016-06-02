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

public class Database {
	private static List<Researcher> researchers;
	private static List<Conference> conferences;
	private static List<Paper> papers;
	private static List<Review> reviews;
	private static String RESEARCHERS_FILE = "pesquisadores.csv";
	private static String CONFERENCES_FILE = "conferencias.csv";
	private static String ARTICLES_FILE = "artigos.csv";
	private static String ATTRIBUTIONS_FILE = "atribuicoes.csv";

	public Database(boolean initData) {
		if (initData) {
			initData();
		}
	}

	public Database() {
		this(true);
	}

	public static void initData() {
		List<String[]> lines = readResourceCSV(RESEARCHERS_FILE);
		for (String[] line : lines) {
			for (String field : line) {
				System.out.print(field + ',');
			}
			System.out.println();
		}
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
}
