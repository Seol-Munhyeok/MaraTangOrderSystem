package com.example.MaraTangOrderSystem.converter;

import com.example.MaraTangOrderSystem.dto.Order.OrderRequestDto;
import com.example.MaraTangOrderSystem.dto.Order.OrderResponseDto;
import com.example.MaraTangOrderSystem.model.Order;

public class OrderConverter {
    public static OrderResponseDto convertToOrderResponseDto(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getUser().getId(),
                order.getTotalPrice()
        );
    }

    public static OrderRequestDto convertToOrderRequestDto(Order order) {
        return new OrderRequestDto(
                order.getId(),
                order.getUser().getId()
        );
    }
}
