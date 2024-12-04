package com.example.MaraTangOrderSystem.controller;

import com.example.MaraTangOrderSystem.dto.Order.OrderRequestDto;
import com.example.MaraTangOrderSystem.dto.Order.OrderResponseDto;
import com.example.MaraTangOrderSystem.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponseDto addOrder(@RequestBody OrderRequestDto orderRequestDto) {
        Long loggedInUserId = getLoggedInUserId();
        return orderService.saveOrder(orderRequestDto);
    }
}
