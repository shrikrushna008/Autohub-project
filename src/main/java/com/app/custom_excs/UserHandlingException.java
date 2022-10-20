package com.app.custom_excs;

public class UserHandlingException extends RuntimeException{

	public UserHandlingException(String mesg) {
		super(mesg);
	}
}
