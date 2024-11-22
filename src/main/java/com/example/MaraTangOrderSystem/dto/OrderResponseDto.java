package com.example.MaraTangOrderSystem.dto;

import java.util.List;

public record OrderResponseDto(Long orderId, Long userId, Integer totalPrice) {}
