package com.DAOs;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.models.User;
import com.util.HibernateUtil;

@Repository
public class UserDAO {
	
	private SessionFactory factory;
	
	@Autowired
	public UserDAO(SessionFactory sessionFactory) {
		this.factory = sessionFactory;
	}
	
	// Login functionality
	public User getUserByCredentials(User logged) {

		// Make a session
		Session session = factory.getCurrentSession();
		
		// Start the named query
		Query query = session.createNamedQuery("findUserByName");
		
		// Pass in parameters
		query.setParameter("name", logged.getUserName());
		query.setParameter("pass", logged.getUserPass());
		
		// Save result set
		List<User> login = query.getResultList();
		
		// Check to see if the list is empty
		if (login.isEmpty()) {
			return null;
		} else {
			return login.get(0);
		}
	}
	
	// Register functionality
	public User addUser(User newUser) {
		
		// Obtain SessionFactory
		try (SessionFactory factory = HibernateUtil.getSessionFactory();
				// Establish a session
				Session session = factory.getCurrentSession()) {
			
			// Begin a transaction
			session.beginTransaction();
			
			// Add user
			session.save(newUser);
			
			// Commit the transaction
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// return the logged user
		return newUser;
	}

}
