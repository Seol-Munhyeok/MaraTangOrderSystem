package com.example.MaraTangOrderSystem.controller;

import com.example.MaraTangOrderSystem.dto.OrderRequestDto;
import com.example.MaraTangOrderSystem.dto.OrderResponseDto;
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
}
