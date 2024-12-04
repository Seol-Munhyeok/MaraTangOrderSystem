package com.example.MaraTangOrderSystem.dto.Order;

import com.example.MaraTangOrderSystem.dto.OrderDetail.OrderDetailDto;

import java.util.List;

public record OrderRequestDto(
        Long orderId,
        Integer spicinessLevel,
        List<OrderDetailDto> orderDetails
) {}

