package main.ui.text.command;

import main.exceptions.BusinessDomainException;

public interface ConferenceUICommand {

	public abstract void execute() throws BusinessDomainException;

}
