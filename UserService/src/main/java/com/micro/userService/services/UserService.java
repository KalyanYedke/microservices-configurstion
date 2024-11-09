package com.micro.userService.services;

import java.util.List;

import com.micro.userService.entities.User;

public interface UserService {
	
	//create user
	 public User saveUser(User user);
	 
	 //getAll user 
	 public List<User> getAllUser();
	 
	 // getUser with singalUser with id

	 public User getUserById(String Id );
	
	 //update
	 public User updateUser(User user);
	 
	 //delete
	 public void deleteUser(String id);
		

}
