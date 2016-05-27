public abstract class ConferenceUI {

	private Map<String,ConferenceUICommand> commandMap;

	private ConferenceUICommand[] conferenceUICommand;

	public abstract void showUI();

	public void addCommand(String key, ConferenceUICommand command) {

	}

}
