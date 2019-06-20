package com.revature.repos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Remark;

@Repository
public class RemarkRepository {
	
	private SessionFactory factory;
	
	@Autowired
	public RemarkRepository(SessionFactory sessionFactory) {
		this.factory = sessionFactory;
	}
	
	// Add a remark
	public Remark add(Remark newRemark) {
		
		// Get a session
		factory.getCurrentSession().save(newRemark);
		
		// Return the added remark back to the user
		return newRemark;
	}
	
	// Delete a remark
	public boolean delete(int remarkId) {
		
		// Make a new session
		Session session = factory.getCurrentSession();
		
		// Get the remark by the id from the data base
		Remark toDelete = session.get(Remark.class, remarkId);
		System.out.println(toDelete);
		
		// Check to see if the retrieved remark existed
		if (toDelete == null) return false;
		session.delete(toDelete);
		return true;
		
	}
	
	// Get remarks by artId
	public List<Remark> getRemarksByArtId(int artId) {
		
		// Create a query finding all the remarks where they have the given artId
		Query query = factory.getCurrentSession().createQuery("from Remark r where r.remarkArtId = :id");
		query.setParameter("id", artId);
		List<Remark> remarks = query.getResultList();
		return remarks;
	}

}
