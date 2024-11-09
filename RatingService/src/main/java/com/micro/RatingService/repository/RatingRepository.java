package com.micro.RatingService.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.micro.RatingService.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating, String>{
	
	
	public List<Rating> findByUserId(String userId);
	
	public List<Rating> findByHotelId(String hotelId);
	

}
