package com.example.MaraTangOrderSystem.service;

import com.example.MaraTangOrderSystem.model.Order;
import com.example.MaraTangOrderSystem.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}