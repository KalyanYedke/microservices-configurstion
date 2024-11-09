package com.micro.RatingService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.RatingService.entities.Rating;
import com.micro.RatingService.repository.RatingRepository;
@Service
public class RatingServiceImp implements RatingServices{
	
	@Autowired
	private RatingRepository ratingRepository;
	
	

	@Override
	public Rating createRating(Rating rating) {
		// TODO Auto-generated method stub
		
		
		return this.ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllReting() {
		// TODO Auto-generated method stub
		
		
		return this.ratingRepository.findAll();
	}

	@Override
	public Rating getRating(String id) {
		// TODO Auto-generated method stub
		
		
		return this.ratingRepository.findById(id).orElseThrow();
	}

	@Override
	public List<Rating> getRatingByUserId(String UserId) {
		// TODO Auto-generated method stub
		
		
		return this.ratingRepository.findByUserId(UserId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String HotelId) {
		// TODO Auto-generated method stub
		
		
		return this.ratingRepository.findByHotelId(HotelId);
	}

	
	
	@Override
	public Rating updateRating(String ratingId, Rating rating) {
		// TODO Auto-generated method stub
		Rating getRaatingId = this.ratingRepository.findById(ratingId).orElseThrow();
		
		getRaatingId.setRating(rating.getRating());
		getRaatingId.setFeedback(rating.getFeedback());
		getRaatingId.setHotelId(rating.getHotelId());
		getRaatingId.setUserId(rating.getUserId());
		
		
		
		Rating updateRating = this.ratingRepository.save(getRaatingId);
		
		return updateRating;
	}

	@Override
	public void deleteRating(String ratingId) {
		// TODO Auto-generated method stub
		
		this.ratingRepository.deleteById(ratingId);
		
	}

}
