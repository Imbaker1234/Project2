package com.revature.exceptions;

public class ShowNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ShowNotFoundException(String message) {
		super(message);
	}
	
	public ShowNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public ShowNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
