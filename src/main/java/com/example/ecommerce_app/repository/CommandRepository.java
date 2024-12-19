package com.example.ecommerce_app.repository;

import com.example.ecommerce_app.entity.Command;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandRepository extends JpaRepository<Command, Long> {


}