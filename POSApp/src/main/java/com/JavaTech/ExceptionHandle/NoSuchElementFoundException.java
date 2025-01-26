package com.JavaTech.ExceptionHandle;

public class NoSuchElementFoundException extends RuntimeException{

	private String message;
	
	public NoSuchElementFoundException() {}
	
	public NoSuchElementFoundException(String message) {
		super(message);
		this.message=message;
	}
}
