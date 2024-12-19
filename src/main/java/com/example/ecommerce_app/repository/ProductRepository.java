package com.example.ecommerce_app.repository;

import com.example.ecommerce_app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
