package com.example.MaraTangOrderSystem.controller;

import com.example.MaraTangOrderSystem.model.*;
import com.example.MaraTangOrderSystem.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add/{ingredientName}")
    @ResponseBody
    public OrderDto addOrder(@PathVariable String ingredientName, @RequestParam Integer quantity) {
        System.out.println("Order received for ingredient: " + ingredientName + ", quantity: " + quantity); // 로그 추가
        Integer ingredientPrice = IngredientType.valueOf(ingredientName).getPrice();
        // DB에 주문 내용 저장
        orderService.saveOrder(ingredientName, ingredientPrice, quantity);
        return new OrderDto(ingredientName, ingredientPrice,quantity);
    }





}
