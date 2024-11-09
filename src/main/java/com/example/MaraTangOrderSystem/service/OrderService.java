package com.example.MaraTangOrderSystem.service;

import com.example.MaraTangOrderSystem.model.Order;
import com.example.MaraTangOrderSystem.model.OrderDto;
import com.example.MaraTangOrderSystem.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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

    public Integer calculateTotalPrice(Integer ingredientPrice, Integer quantity) {
        return quantity * ingredientPrice;
    }

    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            OrderDto orderDto = new OrderDto(order.getIngredientName(), order.getTotalPrice(), order.getQuantity());
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    public OrderDto updateOrderQuantity(Long orderId, Integer newQuantity) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setQuantity(newQuantity);
        order.setTotalPrice(calculateTotalPrice(order.getIngredientPrice(), newQuantity));
        orderRepository.save(order);
        return new OrderDto(order.getIngredientName(), order.getTotalPrice(), order.getQuantity());
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
