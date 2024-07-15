package com.example.springbootcrudapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootcrudapp.model.Product;

public interface ProductRepository  extends JpaRepository<Product, Long>{

}
