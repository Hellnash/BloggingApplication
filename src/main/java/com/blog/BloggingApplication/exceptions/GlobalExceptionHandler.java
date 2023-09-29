package com.blog.BloggingApplication.exceptions;

import java.util.HashMap;
import java.util.Map;

import com.blog.BloggingApplication.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@SuppressWarnings("unused")
public class GlobalExceptionHandler {
	
	/*
	 * if there is any exception in the controller then functions
	 * of this class will be running to handle the exception
	 */
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(UserNotFoundException ex){
		
		String message = ex.getMessage();
		ApiResponse response = new ApiResponse(message, false);
		return new ResponseEntity<> (response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> invalidArgumentException (MethodArgumentNotValidException ex){
		
		Map<String,String> errors = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach(error -> {
				String errorField = ((FieldError) error).getField(); //getting the fields in which validation fails
				String defaultMessage = error.getDefaultMessage(); //getting the error message
				errors.put(errorField, defaultMessage); //putting the errors in the map
				});
		
		return new ResponseEntity<> (errors, HttpStatus.BAD_REQUEST);
	}

}
