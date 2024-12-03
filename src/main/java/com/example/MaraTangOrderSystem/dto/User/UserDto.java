package com.example.MaraTangOrderSystem.dto.User;

import com.example.MaraTangOrderSystem.model.Order;

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
