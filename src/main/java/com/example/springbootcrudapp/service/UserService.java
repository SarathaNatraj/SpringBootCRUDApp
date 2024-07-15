package com.example.springbootcrudapp.service;

import org.springframework.stereotype.Service;

import com.example.springbootcrudapp.model.Order;
import com.example.springbootcrudapp.model.User;
import com.example.springbootcrudapp.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	

	public List<User> listAll(){
		return repo.findAll();
		
	}
	
	public void save(User user) {
		repo.save(user);
	}
	
	public User get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
	public User findUserByUserNameAndPassword(String userName, String password) {
		return repo.findUserByUserNameAndPassword(userName, password);
	}
	
}
