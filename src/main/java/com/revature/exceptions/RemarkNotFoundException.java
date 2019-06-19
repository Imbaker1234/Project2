package com.revature.exceptions;

public class RemarkNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RemarkNotFoundException(String message) {
		super(message);
	}
	
	public RemarkNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public RemarkNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
