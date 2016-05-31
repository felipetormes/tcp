package main.ui.text;


import main.ui.ConferenceUI;

public class ConferenceTextInterface extends ConferenceUI {

	public static String EXIT_CODE = "E" ;

	public String showMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append(UIUtils.getText("message.options"))
				.append(":\n");
		sb.append(UIUtils.getText("message.options.alocacao")).append("\n");
		sb.append(UIUtils.getText("message.options.atribuicao")).append("\n");
		sb.append(UIUtils.getText("message.options.selecao")).append("\n");
		sb.append(UIUtils.getText("message.choose.option")).append(": ");

		return sb.toString();
		
	}

	public void showUI() {

	}

}
