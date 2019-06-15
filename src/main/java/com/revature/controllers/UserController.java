package com.revature.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserService us;
	
	public UserController (UserService userService) {
		this.us = userService;
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public User register(@RequestBody User newUser) {
		return us.register(newUser);		
	}
	
}
