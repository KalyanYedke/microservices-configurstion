package com.micro.hotelService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.hotelService.entites.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

}
