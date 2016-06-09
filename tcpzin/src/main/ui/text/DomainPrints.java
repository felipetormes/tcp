package main.ui.text;

import main.business.domain.Conference;
import main.business.domain.Paper;
import main.business.domain.Researcher;
import main.business.domain.Review;
import main.business.domain.Topic;

public class DomainPrints {

	public static String printConference(Conference conference) {
		String output = UIUtils.getText("message.initials") + ": "
				+ conference.getInitials() + ", "
				+ UIUtils.getText("message.commiteeIDs") + ": ";

		for (Researcher researcher : conference.getCommitteeMembers()) {
			output += researcher.getId() + "/";
		}

		output += ", " + UIUtils.getText("message.papers");
		for (Paper paper : conference.getPapers()) {
			output += paper.getTitle() + "/";
		}
		return output;
	}

	public static String printPaper(Paper paper) {
		String output = UIUtils.getText("message.paperId") + ": "
				+ String.valueOf(paper.getId()) + ", "
				+ UIUtils.getText("message.paperTitle") + ": "
				+ paper.getTitle() + ", "
				+ UIUtils.getText("message.paperAuthor") + ": "
				+ paper.getAuthor().getName() + ", "
				+ UIUtils.getText("message.conference") + ": "
				+ paper.getConference().getInitials() + ", "
				+ UIUtils.getText("message.topics") + ": "
				+ paper.getResearchTopic();
		return output;

	}

	public static String printResearcher(Researcher researcher) {
		String output = UIUtils.getText("message.name") + ": "
				+ researcher.getName() + ", " + UIUtils.getText("message.ID")
				+ ": " + String.valueOf(researcher.getId()) + ", "
				+ UIUtils.getText("message.affiliation") + ": "
				+ researcher.getAffiliation() + ", "
				+ UIUtils.getText("message.topics") + ": ";

		for (Topic topic : researcher.getResearchTopics()) {
			output += topic + "/";
		}

		return output;

	}

	public static String printReview(Review review) {
		String output = UIUtils.getText("message.paper") + ": "
				+ review.getPaper().getId() + ", "
				+ UIUtils.getText("message.reviewerId") + ": "
				+ review.getReviewer().getId() + ", "
				+ UIUtils.getText("message.grade") + ": " + review.getGrade();

		return output;

	}

}