package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.OrderCart;

@Repository
public interface OrderCartRepository extends JpaRepository<OrderCart, Long> {
}
