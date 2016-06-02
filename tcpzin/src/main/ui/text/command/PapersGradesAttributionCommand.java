package main.ui.text.command;
import java.util.List;
import java.util.Map;

import main.business.PapersManagementService;
import main.business.domain.Paper;
import main.business.domain.Researcher;
import main.business.impl.PapersManagementServiceImpl;
import main.ui.text.UIUtils;

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
		Paper chosenPaper = null;
		List<Paper> allPapers = papersManagementService.GetAllPapers();
		System.out.println(UIUtils.getText("message.todosPapers"));
		for (Paper paper : allPapers ){
		System.out.println(UIUtils.getText("message.paperId") + ": "+  paper.getId() + UIUtils.getText("message.paperTitle") + ": "+  paper.getTitle());
		}
		int idPaper = UIUtils.readInteger("message.insiraIdPaper");
		for (Paper paper : allPapers ){
			boolean isChosenPaper = paper.getId() == idPaper;
			if(isChosenPaper)
			{
				chosenPaper = paper;
				break;
			}
		}
		
		
		return chosenPaper;
	
	}

	private Researcher readReviewer() {
	/*	Researcher chosenReviewer = null;
		List<Researcher> allReviwers = papersManagementService.;
		System.out.println(UIUtils.getText("message.todosPapers"));
	
		
		for (Paper paper : allPapers ){
		System.out.println(UIUtils.getText("message.paperId") + ": "+  paper.getId() + UIUtils.getText("message.paperTitle") + ": "+  paper.getTitle());
		}
		int idPaper = UIUtils.readInteger("message.insiraIdPaper");
		for (Paper paper : allPapers ){
			boolean isChosenPaper = paper.getId() == idPaper;
			if(isChosenPaper)
			{
				chosenReviewer = reviewer;
				break;
			}
		}
		
		
		*/
		return null;
	}

	private double readGrade() {
		return UIUtils.readDouble("message.insiraNota");
	}

}
