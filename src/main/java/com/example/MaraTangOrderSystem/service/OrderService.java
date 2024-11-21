package com.example.MaraTangOrderSystem.service;

import com.example.MaraTangOrderSystem.model.Order;
import com.example.MaraTangOrderSystem.model.OrderDto;
import com.example.MaraTangOrderSystem.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    /*private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveOrder(String ingredientName, Integer ingredientPrice, Integer quantity) {
        Integer totalPrice = calculateTotalPrice(ingredientPrice, quantity);
        Order order = new Order(ingredientName, ingredientPrice, quantity, totalPrice);
        orderRepository.save(order);
    }

    private Integer calculateTotalPrice(Integer ingredientPrice, Integer quantity) {
        return quantity * ingredientPrice;
    }

    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return convertToOrderDtos(orders);
    }

    private List<OrderDto> convertToOrderDtos(List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            orderDtos.add(convertToOrderDto(order));
        }
        return orderDtos;
    }

    private OrderDto convertToOrderDto(Order order) {
        return new OrderDto(order.getIngredientName(), order.getTotalPrice(), order.getQuantity());
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
    }*/
}
