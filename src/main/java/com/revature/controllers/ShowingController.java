package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.ShowNotFoundException;
import com.revature.exceptions.UserNotValidException;
import com.revature.models.Principal;
import com.revature.models.ShowErrorResponse;
import com.revature.models.Showing;
import com.revature.services.ShowingService;

@RestController
@RequestMapping("/show")
public class ShowingController {

	private ShowingService ss;
	
	@Autowired
	public ShowingController(ShowingService showingService) {
		this.ss = showingService;
	}
	
	// Create new show
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Showing addShow(@RequestBody Showing newShow, HttpServletRequest request) {
		
		// Get the principal object from the request
		Principal principal = (Principal) request.getAttribute("principal");
		
		// If principal doesn't exist, throw exception
		if (principal == null) {
			throw new UserNotValidException("User not valid");
		}
		
		// If principal's role 
		if (principal.getRole() != 2) {
			throw new UserNotValidException("User not valid");
		}
		
		return ss.addShow(newShow);		
	}
	
	// Update status of show
	@PatchMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Showing updateShow(@RequestBody Showing updateShow, HttpServletRequest request) {
		
		// Get the principal object from the request
		Principal principal = (Principal) request.getAttribute("principal");

		// Put the JSON input into a show object
		Showing show = ss.update(updateShow);
		
		// If principal doesn't exist then user is not authorized
		if (principal == null) {
			throw new UserNotValidException("User not valid");
		}
		
		// If show doesn't exist then no show exists
		if (show == null) {
			throw new ShowNotFoundException("No show with id, " + updateShow.getShowId() + ", found.");
		}
		
		return show;
	}
	
	// Retrieves all shows
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Showing> getAllShows(HttpServletRequest request){
		
		// Get the principal object from the request
		Principal principal = (Principal) request.getAttribute("principal");
		
		// Check to see if the principal exists, if not then return an error
		if (principal == null) {
			throw new UserNotValidException("User not valid");
		}
		
		return this.ss.getAll();
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ShowErrorResponse handleUserNotValidException(UserNotValidException e) {
		ShowErrorResponse error = new ShowErrorResponse();
		error.setStatus(HttpStatus.FORBIDDEN.value());
		error.setMessage(e.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		return error;
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ShowErrorResponse handleNotFoundException(ShowNotFoundException e) {
		ShowErrorResponse error = new ShowErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		return error;
	}
	
}
