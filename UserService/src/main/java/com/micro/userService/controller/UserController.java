package com.micro.userService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.userService.entities.User;
import com.micro.userService.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService; 
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
//	@PostMapping("/user")
//	public User createUser(@RequestBody User user) {
//		
//		
//		
//		return create;
//	}
	
	
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
	    
		//System.out.println("User creation endpoint hit");
		User create =  this.userService.saveUser(user);
	    
		return ResponseEntity.status(HttpStatus.CREATED).body(create);
	}
	
	@GetMapping("/getAllUser")
	public ResponseEntity <List<User>> getAllUser() {
	    System.out.println("get list of user ");
	    List<User> list = this.userService.getAllUser();
	   
	    return ResponseEntity.ok(list); 
	}
	
	int retryCount = 1;
	
	@GetMapping("/getUser/{id}")
	//@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod="ratingHotelFallback")
	//@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingalUser( @PathVariable("id") String id) {
		
		//return this.userService.getUserById(id);
		
		logger.info("get singal user hangeller : UserController");
		logger.info("retry count : {}",retryCount);
		retryCount++;
		User singalUser = userService.getUserById(id);
		
		return ResponseEntity.ok(singalUser);
		
		
		
		
	}
	
	//creating fallbackMerhod foe circuotBreaker
	
//	public ResponseEntity<User> fallbackMethod(String id, Exception ex){
//		
//		logger.info("Fallback is executated because service is doun",ex.getMessage());
//		
////		User user = User.builder().email("dummy@gmail.com");
//		User user = User.builder()
//                .userId("123")
//                .name("Alice")
//                .email("alice@example.com")
//                .about("service is down")
//                .build();
//		
//		return new ResponseEntity<>(user,HttpStatus.OK);
//	}
//	
	public ResponseEntity<User> ratingHotelFallback(String id, Exception ex) {
	    logger.info("Fallback executed due to: {}", ex.getMessage());

	    User user = User.builder()
	            .userId("123")
	            .name("Alice")
	            .email("alice@example.com")
	            .about("Service is down")
	            .build();

	    return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
	
	@PutMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		
		//return this.userService.updateUser(user);
		User update = userService.updateUser(user);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(update);
		
	}
	
	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
		
		this.userService.deleteUser(id);
		
		return ResponseEntity.accepted().build();
		

	}
	
	
	
	
	
	

}
