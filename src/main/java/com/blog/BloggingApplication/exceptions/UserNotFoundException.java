package com.blog.BloggingApplication.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class UserNotFoundException extends RuntimeException{
	
	@Serial
	private static final long serialVersionUID = 1L;
	private String username;
	private String fieldName;
	private long fieldValue;
	
	/*
	 * We are passing user, id strings and userID value to the constructor so
	 * to print message from super keyword as user not found with id : userID
	 */
	
	public UserNotFoundException(String username, String fieldName, long fieldValue) {
		
		super(String.format("%s not found with %s : %s",username, fieldName, fieldValue));
		this.username = username;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
		
}
