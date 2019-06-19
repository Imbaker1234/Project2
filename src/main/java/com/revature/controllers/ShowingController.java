package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.ShowNotFoundException;
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
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Showing addShow(@RequestBody Showing newShow) {
		return ss.addShow(newShow);		
	}
	
	@PatchMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Showing updateShow(@RequestBody Showing updateShow) {
		Showing show = ss.update(updateShow);
		if (show == null) throw new ShowNotFoundException("No show with id, " + updateShow.getShowId() + ", found.");
		return show;
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Showing> getAllShows(){
		return this.ss.getAll();
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
