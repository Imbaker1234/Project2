package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.RemarkNotFoundException;
import com.revature.models.Remark;
import com.revature.models.RemarkErrorResponse;
import com.revature.services.RemarkService;

@RestController
@RequestMapping("/remark")
public class RemarkController {

	private RemarkService rs;
	
	@Autowired
	public RemarkController(RemarkService remarkService) {
		this.rs = remarkService;
	}
	
	// Add remark (POST)
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Remark addRemark(@RequestBody Remark addRemark) {
		Remark addedRemark = rs.addRemark(addRemark);
		return addedRemark;
	}
	
	// Delete remark (DELETE)
	@DeleteMapping(value="/{id}")
	public boolean deleteRemark (@PathVariable int id) {
		boolean deleted = rs.deleteRemark(id);
		if(!deleted) throw new RemarkNotFoundException("No remark with id, " + id + ", found.");
		return deleted;
	}
	
	public RemarkErrorResponse handleNotFoundException(RemarkNotFoundException e) {
		RemarkErrorResponse error = new RemarkErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		return error;
	}
	
}
