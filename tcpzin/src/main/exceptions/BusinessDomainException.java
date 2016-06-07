package main.exceptions;

public class BusinessDomainException extends BusinessException{
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
