package main.ui.text;


import main.business.PapersManagementService;
import main.ui.ConferenceUI;
import main.ui.text.command.ConferenceUICommand;
import main.ui.text.command.PapersAllocationCommand;
import main.ui.text.command.PapersGradesAttributionCommand;
import main.ui.text.command.PapersSelectionCommand;

public class ConferenceTextInterface extends ConferenceUI {

	public static String EXIT_CODE = "E" ;
	private String option;
	private ConferenceUICommand conferenceUIcommand;
	private PapersManagementService papersManagementService;
	public ConferenceTextInterface(PapersManagementService papersManagementService){
		this.papersManagementService = papersManagementService;
	}
	public String showMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append(UIUtils.getText("message.options"))
				.append(":\n");
		sb.append(UIUtils.getText("message.options.alocacao")).append("\n");
		sb.append(UIUtils.getText("message.options.atribuicao")).append("\n");
		sb.append(UIUtils.getText("message.options.selecao")).append("\n");
	

		return sb.toString();
		
	}

	public void showUI() {
		System.out.println(showMenu());
		option = UIUtils.readString(UIUtils.getText("message.choose.option"));
		if(option.contentEquals("A")){
			conferenceUIcommand = new PapersAllocationCommand(papersManagementService);
			conferenceUIcommand.execute();
		}else{
			if(option.contentEquals("T")){
				conferenceUIcommand = new PapersGradesAttributionCommand(papersManagementService);
				conferenceUIcommand.execute();
			}else{
				if(option.contentEquals("S")){
					conferenceUIcommand = new PapersSelectionCommand(papersManagementService);
					conferenceUIcommand.execute();
				}
			}
			
		}
	}

}
