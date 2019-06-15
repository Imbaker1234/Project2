package com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAOs.UserDAO;

@Service
public class UserService {

	private UserDAO ud;
	
	@Autowired
	public UserService(UserDAO userDAO) {
		this.ud = userDAO;
	}
	
}
