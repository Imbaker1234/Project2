package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.models.UserErrorResponse;
import com.revature.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserService us;
	
	@Autowired
	public UserController (UserService userService) {
		this.us = userService;
	}
	
	// Http post request, end point is on register and take in a json and sends out a json
	@PostMapping(value="/register", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	// changes the response status to 201
	@ResponseStatus(HttpStatus.CREATED)
	public User register(@RequestBody User newUser) {
		return us.register(newUser);		
	}
	
	@PostMapping(value="/login", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public User login(@RequestBody User cred) {
		
		// Set a variable to be the return of the object
		User log = us.login(cred);
		
		// Check to see if the return object is null or not
		if (log == null) {
			throw new UserNotFoundException("No user with the username: " + cred.getUserUsername() + ", exists");
		} else {
			return log;
		}
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public UserErrorResponse handleNotFoundException(UserNotFoundException e) {
		UserErrorResponse error = new UserErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		return error;
	}
	
}
