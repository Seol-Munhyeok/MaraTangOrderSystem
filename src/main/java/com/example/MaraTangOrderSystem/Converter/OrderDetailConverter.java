package com.example.MaraTangOrderSystem.Converter;

import com.example.MaraTangOrderSystem.dto.OrderDetail.OrderDetailDto;
import com.example.MaraTangOrderSystem.model.OrderDetail;

public class OrderDetailConverter {
    public static OrderDetailDto convertToOrderDetailDto(OrderDetail orderDetail) {
        return new OrderDetailDto(
                orderDetail.getIngredient().getId(),
                orderDetail.getIngredient().getName(),
                orderDetail.getQuantity(),
                orderDetail.getIngredient().getPrice()
        );
    }
}
