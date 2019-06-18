package com.revature.repos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Showing;

@Repository
public class ShowingRepository {

	private SessionFactory factory;
	
	@Autowired
	public ShowingRepository(SessionFactory sessionFactory) {
		this.factory = sessionFactory;
	}
	
	// Create showing
	public Showing addShow(Showing newShow) {
		
		// Save new showing into the database
		factory.getCurrentSession().save(newShow);
		return newShow;
	}
	
	// Update showing
	public Showing updateShow(Showing updateShow) {
		
		// Open a new session
		Session session = factory.getCurrentSession();
		
		// Get the show you want to update
		Showing show = session.get(Showing.class, updateShow.getShowId());
		
		// Check to see if something was returned or not
		if (show == null) return null;
		
		// Pass the check, set the values of the grabbed show from the database
		show.setShowDescription(updateShow.getShowDescription());
		show.setShowPlace(updateShow.getShowPlace());
		show.setShowStatusId(updateShow.getShowStatusId());
		show.setShowTime(updateShow.getShowTime());
		show.setShowId(updateShow.getShowId());
		
		// Return the updated card back
		return updateShow;
	}
	
}
