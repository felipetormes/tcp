package main.exceptions;

public class BusinessDomainException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessDomainException() {
	}
	
	public BusinessDomainException(String message) {
		super(message);
	}
}
