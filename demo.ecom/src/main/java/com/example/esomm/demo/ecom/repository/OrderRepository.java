package com.example.esomm.demo.ecom.repository;

import com.example.esomm.demo.ecom.Entity.Order;
import com.example.esomm.demo.ecom.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
