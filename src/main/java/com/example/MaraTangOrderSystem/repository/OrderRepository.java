package com.example.MaraTangOrderSystem.repository;

import com.example.MaraTangOrderSystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
