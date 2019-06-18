package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Showing;
import com.revature.repos.ShowingRepository;

@Service
public class ShowingService {

	private ShowingRepository sr;
	
	@Autowired
	public ShowingService(ShowingRepository showingRepository) {
		this.sr = showingRepository;
	}
	
	// Add a show
	@Transactional
	public Showing addShow(Showing show) {
		return sr.addShow(show);
	}
	
	// Update a show
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Showing update(Showing updateShow) {
		if (updateShow.getShowStatusId() == 0) return null;
		return sr.updateShow(updateShow);
	}
	
}
