package com.example.MaraTangOrderSystem.dto;

import java.util.List;

public record OrderResponseDto(Long orderId, String userName, List<OrderDetailDto> orderDetails, Integer totalPrice) {}
