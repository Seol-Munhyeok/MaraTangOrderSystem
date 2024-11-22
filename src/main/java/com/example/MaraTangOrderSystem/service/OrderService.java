package com.example.MaraTangOrderSystem.service;

import com.example.MaraTangOrderSystem.Converter.DtoConverter;
import com.example.MaraTangOrderSystem.dto.OrderRequestDto;
import com.example.MaraTangOrderSystem.dto.OrderResponseDto;
import com.example.MaraTangOrderSystem.model.Order;
import com.example.MaraTangOrderSystem.model.OrderDetail;
import com.example.MaraTangOrderSystem.model.User;
import com.example.MaraTangOrderSystem.repository.IngredientRepository;
import com.example.MaraTangOrderSystem.repository.OrderRepository;
import com.example.MaraTangOrderSystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository, IngredientRepository ingredientRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderResponseDto saveOrder(OrderRequestDto orderRequestDto) {
        Order order = orderRepository.findById(orderRequestDto.orderId()).orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.save(order);
        return DtoConverter.convertToOrderResponseDto(order);
    }

    @Transactional
    public OrderResponseDto updateOrder(OrderRequestDto updatedOrderRequestDto) {
        Long orderId = updatedOrderRequestDto.orderId();
        Order existingOrder = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        existingOrder.setTotalPrice(calculateOrderTotalPrice(existingOrder));
        Order savedOrder = orderRepository.save(existingOrder);
        return DtoConverter.convertToOrderResponseDto(savedOrder);
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Transactional
    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(DtoConverter::convertToOrderResponseDto)
                .toList();
    }

    private Integer calculateOrderTotalPrice(Order order) {
        int totalPrice = 0;
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            if (isMatchingOrder(orderDetail, order)) {
                int price = orderDetail.getIngredient().getPrice();
                int quantity = orderDetail.getQuantity();
                totalPrice += calculateSubtotal(price, quantity);
            }
        }
        return totalPrice;
    }

    private boolean isMatchingOrder(OrderDetail orderDetail, Order order) {
        return Objects.equals(orderDetail.getOrder().getId(), order.getId());
    }

    private Integer calculateSubtotal(Integer ingredientPrice, Integer quantity) {
        return quantity * ingredientPrice;
    }


}
