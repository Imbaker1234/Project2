package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Principal;
import com.revature.models.User;
import com.revature.models.UserErrorResponse;
import com.revature.services.UserService;
import com.revature.util.JwtConfig;
import com.revature.util.JwtGenerator;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserService us;
	
	@Autowired
	public UserController (UserService userService) {
		this.us = userService;
	}
	
	// Http post request, end point is on register and take in a json and sends out a json
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	// changes the response status to 201
	@ResponseStatus(HttpStatus.CREATED)
	public User register(@RequestBody User newUser, HttpServletResponse response) {
		
		// Create the new user
		User reg = us.register(newUser);
		
		// Generate Jwt
		String token = JwtGenerator.createJwt(reg);
					
		// Add the token into the response header
		response.addHeader(JwtConfig.HEADER, JwtConfig.PREFIX + token);
		
		return reg;		
	}
	
	// Add Heart
	@PatchMapping(value="/{id}")
	public User addHeart(@PathVariable("id") String addHeart, HttpServletRequest request) {
		
		// Save the token to a principal object
		Principal principal = (Principal) request.getAttribute("principal");
		
		// Get the user based on id
		User currentUser = us.getUserById(principal.getId());
		
		// Grab hearts list
		String hearts = currentUser.getHearts();

		// Initialize updatedHearts
		String updatedHearts;
		// Add new heart to list, if the first thing is null then set the heart to the first
		if (hearts == null) {
			updatedHearts = addHeart;
		} else {
			updatedHearts = hearts + "," + addHeart;
		}
		
		// Set the updatedHearts to the current user's hearts
		currentUser.setHearts(updatedHearts);
		
		// Update the current user
		User addedHeart = us.addHeart(currentUser);
		
		// Return the updated user
		return addedHeart;
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
