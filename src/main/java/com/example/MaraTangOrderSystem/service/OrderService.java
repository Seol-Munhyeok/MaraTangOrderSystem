package com.example.MaraTangOrderSystem.service;

import com.example.MaraTangOrderSystem.Converter.DtoConverter;
import com.example.MaraTangOrderSystem.dto.OrderRequestDto;
import com.example.MaraTangOrderSystem.model.Order;
import com.example.MaraTangOrderSystem.dto.OrderDto;
import com.example.MaraTangOrderSystem.model.OrderDetail;
import com.example.MaraTangOrderSystem.repository.IngredientRepository;
import com.example.MaraTangOrderSystem.repository.OrderRepository;
import com.example.MaraTangOrderSystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public OrderRequestDto saveOrder(Order order) {
        int totalPrice = calculateOrderTotalPrice(order);
        order.setTotalPrice(totalPrice);
        Order savedOrder = orderRepository.save(order);
        return DtoConverter.convertToOrderDto(savedOrder);
    }

    @Transactional
    public OrderDto updateOrder(Long orderId, Order updatedOrder) {
        Order previousOrder = orderRepository.findById(orderId).orElseThrow();

        previousOrder.getOrderDetails().clear();
        previousOrder.getOrderDetails().addAll(updatedOrder.getOrderDetails());

        int totalPrice = calculateOrderTotalPrice(previousOrder);
        previousOrder.setTotalPrice(totalPrice);

        Order savedOrder =  orderRepository.save(previousOrder);
        return DtoConverter.convertToOrderDto(savedOrder);
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
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

    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return DtoConverter.convertToOrderDtos(orders);
    }
}
