package com.micro.userService.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.micro.userService.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
	
	
	//get
	
	
	//create 
	@PostMapping("/rating/create")
	public Rating createRating(Rating value);
	
	
	//update
	
	@PutMapping("/rating/updateRating/{ratingId}")
	public Rating updateRating(@PathVariable("ratingId") String ratingId, Rating rating);
	
	
	//delete
	@DeleteMapping("/rating/deleteRating/ratingId}")
	public void deleteRating(@PathVariable("ratingId") String ratingId);
}
