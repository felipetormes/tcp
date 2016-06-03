package main.ui.text.command;

import main.business.PapersManagementService;
import main.business.domain.Conference;

public class PapersAllocationCommand implements ConferenceUICommand{

	private PapersManagementService papersManagementService;

	public PapersAllocationCommand(PapersManagementService papersManagementService) {
		this.papersManagementService = papersManagementService;
	}
	
	public void execute(){
		
	}

	private Conference readConference() {
		return null;
	}

	private int readNumReviewers() {
		return 0;
	}

	private void showAllocationLog() {

	}

}
