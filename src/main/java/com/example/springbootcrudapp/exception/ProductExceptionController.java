package com.example.springbootcrudapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionController {

	// For your UIs
	@ExceptionHandler(ProductNotFoundException.class)
	public String prouctNotFoundException(ProductNotFoundException px) {
		System.out.println(px.getLocalizedMessage());
		return "error";
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String prouctIllgel() {
		return "error_demo";
	}

	
	 @ExceptionHandler(IllegalArgumentException.class)
	 public ResponseEntity<?> illegalArgumentException(IllegalArgumentException ix){
		 return new ResponseEntity<>(ix.getLocalizedMessage(), HttpStatus.BAD_REQUEST); 
		}
	 

}
