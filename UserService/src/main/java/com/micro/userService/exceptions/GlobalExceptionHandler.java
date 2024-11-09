package com.micro.userService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.micro.userService.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFOundException(ResourceNotFoundException ex){
		
		
		String messege = ex.getMessage();
		
		ApiResponse responce = ApiResponse.builder().message(messege).success(true).status(HttpStatus.NOT_FOUND).build();
		
		return new ResponseEntity<>(responce,HttpStatus.NOT_FOUND);
		
		
		
	}

}
