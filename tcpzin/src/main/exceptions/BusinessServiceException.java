package main.exceptions;

public class BusinessServiceException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessServiceException() {
	}

	public BusinessServiceException(String message) {
		super(message);
	}
}
