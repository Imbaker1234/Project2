package com.DAOs;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.models.User;
import com.util.HibernateUtil;

public class UserDAO {
	
	public static void main(String[] args) {
		UserDAO ud = new UserDAO();
		User log = new User();
		log.setUserName("Yogi");
		log.setUserPass("mleh");
		log.setUserEmail("whouseshotmail@hotmail.com");
		log.setUserFirst("Rawr");
		log.setUserLast("Bjork");
		User reg = ud.addUser(log);
		System.out.println(reg.getUserID());
		
	}
	
	// Login functionality
	public User getUserByCredentials(User logged) {

		// Initialize the return
		List<User> loggedIn = null;
		
		// Open the SessionFactory
		try (SessionFactory factory = HibernateUtil.getSessionFactory();
				// Establish a session 
				Session session = factory.getCurrentSession()){
			
			// Begin transaction
			session.beginTransaction();
			
			// Get the named query 
			Query query = session.createNamedQuery("findUserByName");
			query.setParameter("name", logged.getUserName());
			query.setParameter("pass", logged.getUserPass());
			
			// Run query and get the result set
			loggedIn = query.getResultList();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Return the logged in user
		return loggedIn.get(0);
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
