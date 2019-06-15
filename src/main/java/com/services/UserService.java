package com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.DAOs.UserDAO;
import com.models.User;

@Service
public class UserService {

	private UserDAO ud;
	
	@Autowired
	public UserService(UserDAO userDAO) {
		this.ud = userDAO;
	}
	
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	public User getByCredentials(User loginUser) {
		return ud.getUserByCredentials(loginUser);
	}
	
}
