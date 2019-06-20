package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.RemarkNotFoundException;
import com.revature.exceptions.UserNotValidException;
import com.revature.models.Principal;
import com.revature.models.Remark;
import com.revature.models.RemarkErrorResponse;
import com.revature.models.UserErrorResponse;
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
	public Remark addRemark(@RequestBody Remark addRemark, HttpServletRequest request) {
		
		// Get the principal from the token
		Principal principal = (Principal) request.getAttribute("principal");
		
		// Check to see if the principal exists 
		if (principal == null) {
			throw new UserNotValidException("User not logged in");
		}
		
		// Add remark to data base
		Remark addedRemark = rs.addRemark(addRemark);
		
		// Return remark
		return addedRemark;
	}
	
	// Delete remark (DELETE)
	@DeleteMapping(value="/{id}")
	public boolean deleteRemark (@PathVariable int id, HttpServletRequest request) {
		
		// Get the principal from the request header
		Principal principal = (Principal) request.getAttribute("principal");
		
		// Check to see if the principal exists
		if (principal == null) {
			throw new UserNotValidException("User not logged in");
		}
		
		// Check to see if the role is of a admin (3)
		if (principal.getRole() != 3) {
			throw new UserNotValidException("User of not the correct role");
		}
		
		// Delete the remark
		boolean deleted = rs.deleteRemark(id);
		
		// Check to see if the remark was deleted
		if(!deleted) {
			throw new RemarkNotFoundException("No remark with id, " + id + ", found.");
		}
		
		// Return the deleted
		return deleted;
	}
	
	// Get remark by ArtId
	@GetMapping(value="/{artId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Remark> getRemarksByArtId(@PathVariable int artId, HttpServletRequest request) {
		
		// Grab the principal object
		Principal principal = (Principal) request.getAttribute("principal");
		
		// Check to see if user is logged in
		if (principal == null) {
			throw new UserNotValidException("User is currently not logged in");
		}
		
		// Return the comments the user is viewing on the art page
		return this.rs.getAllByArtId(artId);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public UserErrorResponse handleUserNotValidException(UserNotValidException e) {
		UserErrorResponse error = new UserErrorResponse();
		error.setStatus(HttpStatus.FORBIDDEN.value());
		error.setMessage(e.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		return error;
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public RemarkErrorResponse handleNotFoundException(RemarkNotFoundException e) {
		RemarkErrorResponse error = new RemarkErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		return error;
	}
	
}
