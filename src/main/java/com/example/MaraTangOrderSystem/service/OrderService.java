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

    public Order saveOrder(String ingredientName, Integer ingredientPrice, Integer quantity) {
        Integer totalPrice = calculateTotalPrice(ingredientPrice, quantity);
        Order order = new Order(ingredientName, ingredientPrice, quantity, totalPrice);
        return orderRepository.save(order);
    }
}
