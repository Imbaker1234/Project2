package com.DAOs;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.models.User;
import com.util.HibernateUtil;

public class UserDAO {
	
	public static void main(String[] args) {
		UserDAO ud = new UserDAO();
		User log = new User();
//		log.setUserName("Munmu");
//		log.setUserPass("cookiesmilk");
//		log.setUserEmail("phailzpanda@gmail.com");
//		log.setUserFirst("Austin");
//		log.setUserLast("Bark");
		User reg = ud.addUser(log);
		System.out.println(reg.getUserID());
		
	}
	
	// Login functionality
	public User getUserByCredentials(User logged) {
		
		// Establish a session 
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		// Begin transaction
		session.beginTransaction();
		
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
	
	// Register functionality
	public User addUser(User newUser) {
		
		// Establish a session
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		// Begin a transaction
		session.beginTransaction();
		
		// Add user
		session.save(newUser);
		
		// Commit the transaction
		session.getTransaction().commit();
		
		// close the session
		session.close();
		
		// return the logged user
		return newUser;
	}

}
