package com.example.MaraTangOrderSystem.Converter;

import com.example.MaraTangOrderSystem.dto.*;
import com.example.MaraTangOrderSystem.model.Ingredient;
import com.example.MaraTangOrderSystem.model.Order;
import com.example.MaraTangOrderSystem.model.OrderDetail;
import com.example.MaraTangOrderSystem.model.User;

public class DtoConverter {
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

    public static User convertToUserDto(SignUpUserDto signUpUserDto, String hashedPassword) {
        return User.create(
                signUpUserDto.email(),
                hashedPassword,
                signUpUserDto.nickname(),
                signUpUserDto.name()
        );
    }

    public static UserDto convertToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                null,
                user.getNickname(),
                user.getName(),
                user.getPhoneNumber(),
                user.getOrders()
        );
    }


    public static IngredientDto convertToUserDto(Ingredient ingredient) {
        return new IngredientDto(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getPrice(),
                ingredient.getOrderDetails());
    }

    public static OrderDetailDto convertToOrderDetailDto(OrderDetail orderDetail) {
        return new OrderDetailDto(
                orderDetail.getIngredient().getId(),
                orderDetail.getIngredient().getName(),
                orderDetail.getQuantity(),
                orderDetail.getIngredient().getPrice()
        );
    }
}
