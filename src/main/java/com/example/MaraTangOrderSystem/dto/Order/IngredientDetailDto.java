package com.example.MaraTangOrderSystem.dto.Order;

public record IngredientDetailDto(
        Long ingredientId,
        String ingredientName,
        Integer quantity
) {}
