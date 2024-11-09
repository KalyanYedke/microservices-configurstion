package com.micro.hotelService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.hotelService.entites.Hotel;
import com.micro.hotelService.exception.ResourceNotFoundException;
import com.micro.hotelService.repository.HotelRepository;
@Service
public class HotelServiceImp implements HotelService{
	
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel createHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		
		Hotel save = this.hotelRepository.save(hotel);
		return save;
	}

	@Override
	public List<Hotel> getAllHotel() {
		// TODO Auto-generated method stub
		
		List<Hotel> getList = this.hotelRepository.findAll();
		
		return getList;
	}

	@Override
	public Hotel getSingleHotel(String id) {
		// TODO Auto-generated method stub
		
		Hotel getSingal = this.hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("hotel with given id not found ....! ")) ;
		
		return getSingal;
	}

	@Override
	public Hotel updateHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		
		Hotel updateHotel = hotelRepository.save(hotel);
		
		return updateHotel;
	}

	@Override
	public void deleteHotel(String id) {
		// TODO Auto-generated method stub
		
		
		this.hotelRepository.deleteById(id);
		
	}
	

}
