package main.ui.text.command;
import java.util.Map;

import main.business.PapersManagementService;
import main.business.domain.Paper;
import main.business.domain.Researcher;
import main.business.impl.PapersManagementServiceImpl;

public class PapersGradesAttributionCommand implements ConferenceUICommand{

	private PapersManagementService papersManagementService;

	private PapersGradesAttributionCommand(PapersManagementService papersManagementService){
		this.papersManagementService = papersManagementService;
	}
	

	public void execute(){
		
	}
	
	
	
	private void showAccRejLists(Map<Paper,Boolean> listsMap) {
		
	}

	private Paper readPaper() {
		return null;
	
	}

	private Researcher readReviewer() {
		return null;
	}

	private double readGrade() {
		return 0;
	}

}
