package com.micro.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.userService.entities.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	

}
