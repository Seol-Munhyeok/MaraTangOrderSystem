package com.example.MaraTangOrderSystem.controller;

import com.example.MaraTangOrderSystem.model.*;
import com.example.MaraTangOrderSystem.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
@ResponseBody
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{ingredientName}")

    public OrderDto addOrder(@PathVariable String ingredientName, @RequestParam Integer quantity) {
        Integer ingredientPrice = IngredientType.valueOf(ingredientName).getPrice();
        orderService.saveOrder(ingredientName, ingredientPrice, quantity);
        return new OrderDto(ingredientName, ingredientPrice,quantity);
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
