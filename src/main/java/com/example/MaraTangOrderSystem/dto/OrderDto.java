package com.example.MaraTangOrderSystem.dto;

import com.example.MaraTangOrderSystem.model.OrderDetail;
import com.example.MaraTangOrderSystem.model.User;

import java.util.List;

public record OrderDto(Long orderId, User user, List<OrderDetail> orderDetails, Integer totalPrice) {}


