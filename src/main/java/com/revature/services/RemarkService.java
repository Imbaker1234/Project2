package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Remark;
import com.revature.repos.RemarkRepository;

@Service
public class RemarkService {

	private RemarkRepository rr;
	
	@Autowired
	public RemarkService(RemarkRepository remarkRepo) {
		this.rr = remarkRepo;
	}
	
	// Add remark
	@Transactional
	public Remark addRemark(Remark add) {
		if (add.getRemarkAuthorId().equals(null) || 
			add.getRemarkDescription().equals(null) || 
			add.getRemarkArtId() == 0 ) {
			return null;
		} 
		return rr.add(add);
	}
	
	// Delete remark
	@Transactional
	public boolean deleteRemark(int id) {
		return rr.delete(id);
	}
	
	// Get all by artId
	@Transactional(readOnly=true, isolation=Isolation.READ_COMMITTED)
	public List<Remark> getAllByArtId(int artId) {
		return this.rr.getRemarksByArtId(artId);
	}
	
}
