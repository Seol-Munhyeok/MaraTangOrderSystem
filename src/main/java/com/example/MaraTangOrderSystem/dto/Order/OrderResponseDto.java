package com.example.MaraTangOrderSystem.dto.Order;

public record OrderResponseDto(
        Long orderId,
        Long userId,
        Integer totalPrice
) {}
