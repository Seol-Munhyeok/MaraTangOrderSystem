package com.example.MaraTangOrderSystem.dto;

import com.example.MaraTangOrderSystem.model.Order;
import com.example.MaraTangOrderSystem.model.OrderDetail;

import java.util.List;

public record UserDto(
        Long userId,
        String email,
        String password,
        String nickname,
        String name,
        String phoneNumber,
        List<Order> orders
) {}
