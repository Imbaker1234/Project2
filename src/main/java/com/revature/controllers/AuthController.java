package com.revature.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.revature.util.JwtConfig;
import com.revature.util.JwtGenerator;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

	private UserService us;
	
	@Autowired
	public AuthController(UserService userService) {
		this.us = userService;
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public User login(@RequestBody User cred, HttpServletResponse response) {
		
		// Set a variable to be the return of the object
		User log = us.login(cred);
		
		// Check to see if the return object is null or not
		if (log == null) {
			throw new UserNotFoundException("No user with the username: " + cred.getUserUsername() + ", exists");
		} else {

			// Generate Jwt
			String token = JwtGenerator.createJwt(log);
			
			// Add the token into the response header
			response.addHeader(JwtConfig.HEADER, JwtConfig.PREFIX + token);
			
			// Return the User in the body of the response
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
