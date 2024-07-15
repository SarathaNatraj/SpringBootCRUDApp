package com.example.springbootcrudapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootcrudapp.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
