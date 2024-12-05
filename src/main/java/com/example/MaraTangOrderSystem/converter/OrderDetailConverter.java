package com.example.MaraTangOrderSystem.converter;

import com.example.MaraTangOrderSystem.dto.Order.OrderDetailDto;
import com.example.MaraTangOrderSystem.model.OrderDetail;

public class OrderDetailConverter {
    public static OrderDetailDto convertToOrderDetailDto(OrderDetail orderDetail) {
        return new OrderDetailDto(
                orderDetail.getIngredient().getId(),
                orderDetail.getQuantity()
        );
    }
}
