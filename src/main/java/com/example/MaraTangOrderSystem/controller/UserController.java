package com.example.MaraTangOrderSystem.controller;

import com.example.MaraTangOrderSystem.dto.OrderRequestDto;
import com.example.MaraTangOrderSystem.dto.OrderResponseDto;
import com.example.MaraTangOrderSystem.dto.UserDto;
import com.example.MaraTangOrderSystem.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }
}
