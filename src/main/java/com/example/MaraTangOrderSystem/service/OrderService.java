package com.example.MaraTangOrderSystem.service;

import com.example.MaraTangOrderSystem.Converter.DtoConverter;
import com.example.MaraTangOrderSystem.model.Order;
import com.example.MaraTangOrderSystem.dto.OrderDto;
import com.example.MaraTangOrderSystem.repository.IngredientRepository;
import com.example.MaraTangOrderSystem.repository.OrderRepository;
import com.example.MaraTangOrderSystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, IngredientRepository ingredientRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public OrderDto saveOrder(Order order) {
        // TODO
        orderRepository.save(order);
    }

    private Integer calculateTotalPrice(Integer ingredientPrice, Integer quantity) {
        return quantity * ingredientPrice;
    }

    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return DtoConverter.convertToOrderDtos(orders);
    }

    @Transactional
    public OrderDto updateOrder(Long orderId, Order updatedOrder) {
        Order previousOrder = orderRepository.findById(orderId).orElseThrow();
        // 이전에 getOrderDetails에 있는 내용을 삭제하고 updatedOrder의 값으로 수정

        // 다시 계산한 최종 가격을 previousOrder에 반영하고 previousOrder를 리턴

        orderRepository.save(previousOrder);
        return new OrderDto(order.getIngredientName(), order.getTotalPrice(), order.getQuantity());
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
