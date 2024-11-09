package com.micro.userService.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.micro.userService.entities.Hotel;
import com.micro.userService.entities.Rating;
import com.micro.userService.entities.User;
import com.micro.userService.exceptions.ResourceNotFoundException;
import com.micro.userService.external.service.HotelService;
import com.micro.userService.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository ;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		
		//genrate unique id
//		String randomUserId = UUID.randomUUID().toString();// use for id we delclear a string userid 
//		user.setUserId(randomUserId);
			
		User userSave = this.userRepository.save(user);
		
			
		return userSave;
	}

	@Override
	public List<User> getAllUser() {
		
		List<User> list =this.userRepository.findAll();
		
		return list;
	}

	@Override
	public User getUserById(String Id) {
		
		// this user fetch from database using userarepository
		
		User userById = this.userRepository.findById(Id).orElseThrow(()-> new ResourceNotFoundException("user not found given id"+Id));
		//fetch rating of the abhove userid from rating service
		
		//http://localhost:8082/rating/user/1
		
		// ArrayList<Rating> ratingOfUser = restTemplate.getForObject("localhost:8082/rating/user/2", ArrayList.class);
		 Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/rating/user/"+Id, Rating[].class);
		 
		 List<Rating> ratings = Arrays.stream(ratingOfUser).toList(); 

		 
		List<Rating> ratingList = ratings.stream().map( rating -> {
			 
			//api call hotel service to get the hotel   //http://localhost:8081/hotel/getHotel/1
			 //set the hotel to rating and return the rating
			 
			//ResponseEntity< Hotel> getEntity =restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/getHotel/"+rating.getHotelId(),Hotel.class);
		
			//Hotel hotel = getEntity.getBody();
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			
			
			//logger.info("responce status code: {} ",getEntity.getStatusCode());
			
			rating.setHotel(hotel);
			return rating;
			
		}).collect(Collectors.toList());
		 
		logger.info("{} ",ratingOfUser);
		
		userById.setRatings(ratings);
		
		return userById;
	}
	
//	public User getUserById(String Id) {
//	    // Fetch user from database using userRepository
//	    User userById = this.userRepository.findById(Id)
//	        .orElseThrow(() -> new ResourceNotFoundException("User not found for the given id: " + Id));
//	    
//	    // Fetch ratings of the above userId from the Rating Service
//	    // Dynamic URL: localhost:8082/rating/user/{Id}
//	    String url = "http://localhost:8082/rating/user/" + Id;
//	    
//	    ArrayList<Rating> ratingOfUser;
//	    try {
//	        // Fetch the ratings using RestTemplate
//	        ratingOfUser = restTemplate.getForObject(url, ArrayList.class);
//	    } catch (Exception e) {
//	        // Log error and handle the exception (optional)
//	        logger.error("Error fetching ratings from Rating Service for userId: {}", Id, e);
//	        ratingOfUser = new ArrayList<>();  // Return an empty list if error occurs
//	    }
//	    
//	    logger.info("Fetched ratings: {}", ratingOfUser);
//	    
//	    // Set the fetched ratings to the user object
//	    userById.setRatings(ratingOfUser);
//	    
//	    return userById;
//	}


	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		
		User update = this.userRepository.save(user);
		
		return update;
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		
		this.userRepository.deleteById(id);
		
	}

}
