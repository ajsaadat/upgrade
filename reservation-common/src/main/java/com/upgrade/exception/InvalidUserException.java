package com.upgrade.exception;

public class InvalidUserException extends ValidationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidUserException(String message){
		super(message) ; 
	}

}
