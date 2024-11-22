package com.example.MaraTangOrderSystem.repository;

import com.example.MaraTangOrderSystem.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
