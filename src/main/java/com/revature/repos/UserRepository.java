package com.revature.repos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
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
		
		// Save user to the database
		factory.getCurrentSession().save(newUser);
		return newUser;
	}
	
	// Check to see if the user exists, if they do give the credentials back
	public User getByCredentials(User loggedUser) {
		
		// Make a new session
		Session session = factory.getCurrentSession();
		
		// Save the query writen in the named queries
		Query query = session.createNamedQuery("findUserByCred");
		
		// Set the parameters
		query.setParameter("username", loggedUser.getUserUsername());
		query.setParameter("password", loggedUser.getUserPassword());
		
		// Save result set
		List<User> login = query.getResultList();
		
		// Check to see if the user is empty
		if (login.isEmpty()) {
			return null;
		} else {
			return login.get(0);
		}
	}
	
}
