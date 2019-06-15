package com.revature.repos;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.User;

@Repository
public class UserRepository {
	
	private SessionFactory factory;
	
	@Autowired
	public UserRepository(SessionFactory sessionFactory) {
		this.factory = sessionFactory;
	}
	
	// Register a new user
	public User register(User newUser) {
		factory.getCurrentSession().save(newUser);
		return newUser;
	}
	
}
