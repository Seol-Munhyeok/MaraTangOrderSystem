package com.example.MaraTangOrderSystem.dto.Order;

import java.util.List;

public record OrderRequestDto(
        Long orderId,
        Integer spicinessLevel,
        List<OrderDetailDto> orderDetails
) {}

