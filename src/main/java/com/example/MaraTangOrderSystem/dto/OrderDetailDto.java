package com.example.MaraTangOrderSystem.dto;

public record OrderDetailDto(
        Long ingredientId,
        String ingredientName,
        Integer quantity,
        Integer price
) {}
