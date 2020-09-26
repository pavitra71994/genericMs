package com.example.examMicroservice.exceptions;

public class NewApplicationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4348167615288391785L;
	private String message;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public NewApplicationException(String message) {
		super();
		this.setMessage(message);
	}
	public NewApplicationException() {
		super();
	}
}
