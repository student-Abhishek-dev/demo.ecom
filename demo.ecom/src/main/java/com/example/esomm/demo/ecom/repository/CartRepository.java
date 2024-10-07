package com.example.esomm.demo.ecom.repository;


import com.example.esomm.demo.ecom.Entity.Cart;
import com.example.esomm.demo.ecom.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId); // This method already exists
    Optional<Cart> findByUser(User user); // Add this method if you want to find by User entity
}

