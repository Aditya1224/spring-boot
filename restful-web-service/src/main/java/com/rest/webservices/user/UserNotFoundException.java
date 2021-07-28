package com.rest.webservices.user;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/*
 * I would want to use @ResponseStatus(HttpStatus.NOT_FOUND).
 * Because the resource is not really found.
 * So I'm going to use not found HTTP status called not found.
 * 
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{
	
	public UserNotFoundException(String message) {
		super(message);
	}

}
