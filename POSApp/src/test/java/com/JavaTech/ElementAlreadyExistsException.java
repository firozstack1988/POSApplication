package com.JavaTech.ExceptionHandle;

public class ElementAlreadyExistsException extends RuntimeException{
   
	private String message;
	public ElementAlreadyExistsException() {}
	
	public ElementAlreadyExistsException(String message) {
		super(message);
		this.message=message;
	}

}
