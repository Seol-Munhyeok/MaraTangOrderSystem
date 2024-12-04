package com.example.MaraTangOrderSystem.controller;

import com.example.MaraTangOrderSystem.dto.Order.OrderRequestDto;
import com.example.MaraTangOrderSystem.dto.Order.OrderResponseDto;
import com.example.MaraTangOrderSystem.model.Order;
import com.example.MaraTangOrderSystem.model.User;
import com.example.MaraTangOrderSystem.repository.UserRepository;
import com.example.MaraTangOrderSystem.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserRepository userRepository;

    public OrderController(OrderService orderService, UserRepository userRepository) {
        this.orderService = orderService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> addOrder(@RequestBody OrderRequestDto orderRequestDto, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        OrderResponseDto responseDto = orderService.createOrderAndSave(orderRequestDto, user);
        return ResponseEntity.ok(responseDto);
    }
}