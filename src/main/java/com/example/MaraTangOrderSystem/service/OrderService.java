package com.example.MaraTangOrderSystem.service;

import com.example.MaraTangOrderSystem.converter.OrderConverter;
import com.example.MaraTangOrderSystem.dto.Order.*;
import com.example.MaraTangOrderSystem.model.Order;
import com.example.MaraTangOrderSystem.model.OrderDetail;
import com.example.MaraTangOrderSystem.model.User;
import com.example.MaraTangOrderSystem.repository.IngredientRepository;
import com.example.MaraTangOrderSystem.repository.OrderRepository;
import com.example.MaraTangOrderSystem.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
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
    public OrderResponseDto createOrderAndSave(OrderRequestDto orderRequestDto, User user) {
        Order order = OrderConverter.convertToOrder(orderRequestDto, user, ingredientRepository);
        int totalPrice = calculateSingleOrderPrice(order);
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);

        return OrderConverter.convertToOrderResponseDto(order);
    }

    @Transactional(readOnly = true)
    public ReceiptDto issueReceipt(Long userId) {
        User user = findUserById(userId);
        List<Order> orders = orderRepository.findByUserId(userId);

        int totalPrice = calculateTotalOrdersPrice(orders);
        List<OrderSummaryDto> orderSummaries = createOrderSummaries(orders);

        return createReceipt(user, orderSummaries, totalPrice);
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 이용자가 없습니다"));
    }

    private List<OrderSummaryDto> createOrderSummaries(List<Order> orders) {
        List<OrderSummaryDto> orderSummaries = new ArrayList<>();
        for (Order order : orders) {
            orderSummaries.add(createOrderSummary(order));
        }
        return orderSummaries;
    }

    private OrderSummaryDto createOrderSummary(Order order) {
        List<IngredientDetailDto> ingredientDetailDtos = createIngredientDetails(order.getOrderDetails());
        return new OrderSummaryDto(
                order.getId(),
                order.getSpicinessLevel(),
                ingredientDetailDtos,
                order.getTotalPrice()
        );
    }

    private List<IngredientDetailDto> createIngredientDetails(List<OrderDetail> orderDetails) {
        List<IngredientDetailDto> ingredientDetailDtos = new ArrayList<>();
        for (OrderDetail detail : orderDetails) {
            ingredientDetailDtos.add(createIngredientDetail(detail));
        }
        return ingredientDetailDtos;
    }

    private IngredientDetailDto createIngredientDetail(OrderDetail detail) {
        return new IngredientDetailDto(
                detail.getIngredient().getId(),
                detail.getIngredient().getName(),
                detail.getQuantity()
        );
    }

    private ReceiptDto createReceipt(User user, List<OrderSummaryDto> orderSummaries, int totalPrice) {
        return new ReceiptDto(
                user.getId(),
                user.getName(),
                orderSummaries,
                totalPrice
        );
    }

    private int calculateTotalOrdersPrice(List<Order> orders) {
        int totalPrice = 0;
        for (Order order : orders) {
            totalPrice += calculateSingleOrderPrice(order);
        }
        return totalPrice;
    }

    @Transactional
    public OrderResponseDto updateOrder(OrderRequestDto updatedOrderRequestDto) {
        Long orderId = updatedOrderRequestDto.orderId();
        Order existingOrder = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        existingOrder.setTotalPrice(calculateSingleOrderPrice(existingOrder));
        Order savedOrder = orderRepository.save(existingOrder);
        return OrderConverter.convertToOrderResponseDto(savedOrder);
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderConverter::convertToOrderResponseDto)
                .toList();
    }

    private Integer calculateSingleOrderPrice(Order order) {
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
