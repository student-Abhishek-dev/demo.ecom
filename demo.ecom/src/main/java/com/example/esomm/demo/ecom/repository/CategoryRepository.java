package com.example.esomm.demo.ecom.repository;

import com.example.esomm.demo.ecom.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
