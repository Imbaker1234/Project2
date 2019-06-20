package com.revature.exceptions;

public class UserNotValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotValidException(String message) {
		super(message);
	}
	
	public UserNotValidException(Throwable cause) {
		super(cause);
	}
	
	public UserNotValidException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
