package com.DAOs;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.models.User;
import com.util.HibernateUtil;

public class UserDAO {
	
	public User getUserByCredentials(User logged) {
		
		// Establish a session 
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		// Get the named query 
		Query query = session.createNamedQuery("findUserByName");
		query.setParameter("name", logged.getUserName());
		query.setParameter("pass", logged.getUserPass());
		
		// Run query and get the result set
		List<User> loggedIn = query.getResultList();
		
		// Close the session
		session.close();
		
		// Return the logged in user
		return loggedIn.get(0);
		
	}

}
