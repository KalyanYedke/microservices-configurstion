package com.micro.hotelService.service;

import java.util.List;

import com.micro.hotelService.entites.Hotel;

public interface HotelService {
	
	
	//create
	public Hotel createHotel(Hotel hotel);
	
	//gethotel
	
	public List<Hotel> getAllHotel();
	
	
	public Hotel getSingleHotel(String id);
	
	
	//update
	
	public Hotel updateHotel(Hotel hotel);
	
	
	//delete
	
	public void deleteHotel(String id);
	

}
