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
	
	// Update user with new hearts
	public User updateUser(User currentUser) {
		
		// Make a new session
		Session session = factory.getCurrentSession();
		
		// Get the current user from the database, and save it in a user object
		User update = session.get(User.class, currentUser.getUserId());
		
		// Check to see if the retrieved user exists
		if (update == null) return null;
		
		// Save the updated values
		update.setUserId(currentUser.getUserId());
		update.setUserUsername(currentUser.getUserUsername());
		update.setUserPassword(currentUser.getUserPassword());
		update.setUserFirstname(currentUser.getUserFirstname());
		update.setUserLastname(currentUser.getUserLastname());
		update.setUserRole(currentUser.getUserRole());
		update.setUserEmail(currentUser.getUserEmail());
		update.setHearts(currentUser.getHearts());
		
		// Return the updated user
		return update;
	}
	
	// Get user by id
	public User getById(int id) {
		return factory.getCurrentSession().get(User.class, id);
	}
	
}
