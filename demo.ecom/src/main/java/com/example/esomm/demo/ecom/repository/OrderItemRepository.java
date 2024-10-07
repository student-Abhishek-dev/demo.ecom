package com.example.esomm.demo.ecom.repository;

import com.example.esomm.demo.ecom.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
