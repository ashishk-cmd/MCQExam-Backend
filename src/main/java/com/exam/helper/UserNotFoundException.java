package com.exam.helper;

public class UserNotFoundException extends Exception {

	public UserNotFoundException() {
		super("User with username not found !! ");
	}

	public UserNotFoundException(String message) {
		super(message);
	}
	
	

}
