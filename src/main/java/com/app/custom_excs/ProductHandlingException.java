package com.app.custom_excs;

@SuppressWarnings("serial")
public class ProductHandlingException extends RuntimeException{
	public ProductHandlingException(String mesg) {
		super(mesg);
	}
}
