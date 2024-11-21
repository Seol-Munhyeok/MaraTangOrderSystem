package com.example.MaraTangOrderSystem.dto;

import com.example.MaraTangOrderSystem.model.Order;
import com.example.MaraTangOrderSystem.model.OrderDetail;

import java.util.List;

public record UserDto(Long orderId, String name, String phoneNumber, Integer spicinessLevel, List<Order> orders) {}
