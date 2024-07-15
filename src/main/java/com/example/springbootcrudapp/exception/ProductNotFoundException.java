package com.example.springbootcrudapp.exception;

public class ProductNotFoundException extends IllegalArgumentException{
	
	private static final long serialVersionUID = 1L;
	
	public ProductNotFoundException(Long id) {
		super(String.format("Product with Id %d not found",id));
	}

}
