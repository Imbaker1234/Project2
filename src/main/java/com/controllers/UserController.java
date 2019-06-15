package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.models.User;
import com.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService us;
	
	@Autowired
	public UserController(UserService userService) {
		this.us = userService;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public User login(@RequestBody User logUser) {
		User loggedUser = us.getByCredentials(logUser);
		return loggedUser;
	}
	
}
