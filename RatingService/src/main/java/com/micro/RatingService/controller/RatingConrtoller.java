package com.micro.RatingService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.RatingService.entities.Rating;
import com.micro.RatingService.service.RatingServices;

@RestController
@RequestMapping("/rating")
public class RatingConrtoller {
	
	@Autowired
	private RatingServices ratingServices;
	
	
	//create
	@PostMapping("/create")
	public ResponseEntity<Rating> createReting(@RequestBody Rating rating){
		
		Rating ratings = this.ratingServices.createRating(rating);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ratings);
	}
	@GetMapping("/getAll")
	public ResponseEntity<List<Rating>> getAllReting(){
		
		List<Rating> list = this.ratingServices.getAllReting();
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	
	
	@GetMapping("/getSingalRating/{id}")
	public ResponseEntity<Rating> getSIngalRating(@PathVariable("id") String Id){
		
		Rating rating = this.ratingServices.getRating(Id);
		
		return ResponseEntity.status(HttpStatus.OK).body(rating);
		
	}
	
	
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Rating>> getRatingUserId(@PathVariable("userId") String userId){
		
		List<Rating> listByUserId = this.ratingServices.getRatingByUserId(userId);
		
		return ResponseEntity.status(HttpStatus.OK).body(listByUserId);
	}
	
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingHotelId(@PathVariable("hotelId") String hotelId){
		
		List<Rating> listByhotelId = this.ratingServices.getRatingByHotelId(hotelId);
		
		return ResponseEntity.status(HttpStatus.OK).body(listByhotelId);
	}
	
	@PutMapping("/updateRating/{id}")
	public ResponseEntity<Rating> updateRating(@PathVariable("id") String Id, @RequestBody Rating rating){
			
			Rating ratingUpdate = this.ratingServices.updateRating(Id, rating);
			
			return ResponseEntity.status(HttpStatus.OK).body(ratingUpdate);
			
		}
	
	@DeleteMapping("/deleteRating/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
		
		this.ratingServices.deleteRating(id);
		
		return ResponseEntity.accepted().build();
		

	}
	
	

}
