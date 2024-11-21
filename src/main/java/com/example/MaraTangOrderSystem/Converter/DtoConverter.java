package com.example.MaraTangOrderSystem.Converter;

import com.example.MaraTangOrderSystem.dto.IngredientDto;
import com.example.MaraTangOrderSystem.dto.OrderDto;
import com.example.MaraTangOrderSystem.dto.UserDto;
import com.example.MaraTangOrderSystem.model.Ingredient;
import com.example.MaraTangOrderSystem.model.Order;
import com.example.MaraTangOrderSystem.model.User;

import java.util.ArrayList;
import java.util.List;

public class DtoConverter {
    public static List<OrderDto> convertToOrderDtos(List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            orderDtos.add(convertToOrderDto(order));
        }
        return orderDtos;
    }

    public static OrderDto convertToOrderDto(Order order) {
        return new OrderDto(order.getId(), order.getUser(), order.getOrderDetails(), order.getTotalPrice());
    }

    public static UserDto convertToUserDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getPhoneNumber(), user.getSpicinessLevel(), user.getOrders());
    }

    public static IngredientDto convertToUserDto(Ingredient ingredient) {
        return new IngredientDto(ingredient.getId(), ingredient.getName(), ingredient.getPrice(), ingredient.getOrderDetails());
    }
}
