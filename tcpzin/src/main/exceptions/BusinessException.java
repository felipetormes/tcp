package main.exceptions;

public abstract class BusinessException extends Exception{

	public BusinessException() {
	}
	
	public BusinessException(String message) {
		super(message);
	}

}
