package com.micro.RatingService.service;

import java.util.List;

import com.micro.RatingService.entities.Rating;

public interface RatingServices {
	
	//create
	
	public Rating createRating(Rating rating) ;
	
	//get ratings
	
	public List<Rating> getAllReting();
	
	public Rating getRating(String id);
	
	
	public List<Rating> getRatingByUserId(String UserId);
	
	public List<Rating> getRatingByHotelId(String HotelId);
	
	//update
	
	public Rating updateRating(String ratingId, Rating rating);

	//delete
	public void deleteRating(String ratingId);

}
