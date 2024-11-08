package com.example.MaraTangOrderSystem.controller;

import com.example.MaraTangOrderSystem.model.*;
import com.example.MaraTangOrderSystem.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add/{ingredientName}")
    @ResponseBody
    public OrderDto addOrder(@PathVariable String ingredientName, Integer quantity) {
        Integer ingredientPrice = IngredientType.valueOf(ingredientName).getPrice();
        // DB에 주문 내용 저장
        orderService.saveOrder(ingredientName, ingredientPrice, quantity);
        return new OrderDto(ingredientName, ingredientPrice,quantity);
    }





}
