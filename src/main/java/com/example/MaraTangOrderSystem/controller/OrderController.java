package com.example.MaraTangOrderSystem.controller;

import com.example.MaraTangOrderSystem.model.Ingredient;
import com.example.MaraTangOrderSystem.model.IngredientDto;
import com.example.MaraTangOrderSystem.model.IngredientType;
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
    public IngredientDto addOrder(@PathVariable String ingredientName) {
        Integer ingredientPrice = IngredientType.valueOf(ingredientName).getPrice();
        Ingredient ingredient = new Ingredient(ingredientName, ingredientPrice);
        return new IngredientDto(ingredient.getName(), ingredient.getPrice());
    }

}
