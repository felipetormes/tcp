package main.exceptions;

public class CommandTextException extends Exception {
	private static final long serialVersionUID = 1L;

	public CommandTextException() {
	}
	
	public CommandTextException(String message) {
		super(message);
	}
}