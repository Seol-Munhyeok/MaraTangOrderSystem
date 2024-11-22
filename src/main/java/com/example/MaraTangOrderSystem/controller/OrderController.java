package com.example.MaraTangOrderSystem.controller;

import com.example.MaraTangOrderSystem.dto.OrderDto;
import com.example.MaraTangOrderSystem.dto.OrderRequestDto;
import com.example.MaraTangOrderSystem.dto.OrderResponseDto;
import com.example.MaraTangOrderSystem.model.*;
import com.example.MaraTangOrderSystem.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponseDto addOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.saveOrder(orderRequestDto);
    }

    @GetMapping
    public List<OrderDto> viewOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/{orderId}")
    public OrderDto editOrder(@PathVariable Long orderId, @RequestParam Integer newQuantity) {
        return orderService.updateOrderQuantity(orderId, newQuantity);
    }

    @DeleteMapping("/{orderId}")
    public void cancelOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }
}
