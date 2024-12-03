package com.example.MaraTangOrderSystem.dto.Ingredient;

import com.example.MaraTangOrderSystem.model.OrderDetail;

import java.util.List;

public record IngredientDto(
        Long orderId,
        String name,
        Integer price,
        List<OrderDetail> orderDetails) {}