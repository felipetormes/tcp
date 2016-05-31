package main.ui.text.command;

import main.business.PapersManagementService;
import main.business.domain.Conference;
import main.business.domain.Paper;

import java.util.Map;

public class PapersSelectionCommand implements ConferenceUICommand{

	private PapersManagementService papersManagementService;

	public void PapersSelectionCommand(PapersManagementService papersManagementService) {
		this.papersManagementService = papersManagementService;
	}

	public void execute(){
		
	}
	
	private Conference readConference() {
		return null;
	}

	private void showGradeMissingAlert() {

	}

	private void showAccRejLists(Map<Paper,Boolean> listsMap) {

	}

}
