package com.example.MaraTangOrderSystem.dto.OrderDetail;

public record OrderDetailDto(
        Long ingredientId,
        String ingredientName,
        Integer quantity,
        Integer price
) {}
