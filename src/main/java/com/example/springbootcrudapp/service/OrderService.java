package com.example.springbootcrudapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootcrudapp.model.Order;
import com.example.springbootcrudapp.model.Product;
import com.example.springbootcrudapp.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderRepository repo;
	
	public List<Order> listAll(){
		return repo.findAll();
		
	}
	
	public void save(Order order) {
		repo.save(order);
	}
	
	public Order get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}

}
