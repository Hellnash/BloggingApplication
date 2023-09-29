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
	private String fieldname;
	private long fieldvalue;
	
	/*
	 * We are passing user, id strings and userID value to the constructor so
	 * to print message from super keyword as user not found with id : userID
	 */
	
	public UserNotFoundException(String username, String fieldname, long fieldvalue) {
		
		super(String.format("%s not found with %s : %s",username, fieldname, fieldvalue));
		this.username = username;
		this.fieldname = fieldname;
		this.fieldvalue = fieldvalue;
	}
		
}
