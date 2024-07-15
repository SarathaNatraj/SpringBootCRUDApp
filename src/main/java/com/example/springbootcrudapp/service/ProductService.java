package com.example.springbootcrudapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootcrudapp.model.Product;
import com.example.springbootcrudapp.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository repo;
	
	public List<Product> listAll(){
		return repo.findAll();
		
	}
	
	public void save(Product product) {
		repo.save(product);
	}
	
	public Product get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
	
	public List<String> findAllProductNames() {
		
	
		List<Product> products = repo.findAll();
		List<String> prodNames = products.stream()
				.map(Product::getName)
				.collect(Collectors.toList());
		
		return prodNames;
	}
	
}
