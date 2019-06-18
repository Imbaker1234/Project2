package com.revature.repos;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RemarkRepository {
	
	private SessionFactory factory;
	
	@Autowired
	public RemarkRepository(SessionFactory sessionFactory) {
		this.factory = sessionFactory;
	}
	
	// Add a remark
	
	// Delete a remark

}
