package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.User;
import com.revature.repos.UserRepository;

@Service
public class UserService {
	
	private UserRepository ur;
	
	@Autowired
	public UserService(UserRepository userRepo) {
		this.ur = userRepo;
	}
	
	@Transactional
	public User register(User user) {
		if(user.getUserUsername().equals("") ||
		   user.getUserPassword().equals("") || 
		   user.getUserFirstname().equals("")|| 
		   user.getUserLastname().equals("") || 
		   user.getUserEmail().equals("")) {
			return null;
		}
		if (user.getUserUsername() == null ||
			user.getUserPassword() == null ||
			user.getUserFirstname() == null||
			user.getUserLastname() == null ||
			user.getUserEmail() == null) {
			return null;
		}
		return ur.register(user);
		
	}
	
	@Transactional
	public User login(User user) {
		User loggedIn = ur.getByCredentials(user);
		if (loggedIn == null) {
			return null;
		} else {
			return loggedIn;
		}
	}
	
	@Transactional
	public User getUserById(int id) {
		return this.ur.getById(id);
	}
	
	@Transactional
	public User addHeart(User currentUser) {
		return this.ur.updateUser(currentUser);
	}

}
