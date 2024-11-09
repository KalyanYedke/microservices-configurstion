package com.micro.hotelService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.hotelService.entites.Hotel;
import com.micro.hotelService.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	
	//create
	@PostMapping("/create")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
		
		Hotel createHotel = this.hotelService.createHotel(hotel);
		
		
		return  ResponseEntity.status(HttpStatus.CREATED).body(createHotel);
		
	}
	@GetMapping("/getHotel/{id}")
	public ResponseEntity<Hotel> getSingalHotel(@PathVariable String id){
		
			Hotel getHotel = this.hotelService.getSingleHotel(id);
		
		
		return  ResponseEntity.status(HttpStatus.OK).body(getHotel);
		
	
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Hotel>> getAllHotel(){
		
		List<Hotel> listHotel = this.hotelService.getAllHotel(); 
		
		return ResponseEntity.ok(listHotel);
		
		
	}
	 
	

}
