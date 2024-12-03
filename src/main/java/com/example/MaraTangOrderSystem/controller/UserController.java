package com.example.MaraTangOrderSystem.controller;

import com.example.MaraTangOrderSystem.dto.*;
import com.example.MaraTangOrderSystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUpUser(@RequestBody SignUpUserDto signUpUserDto) {
        UserDto userDto = userService.signUpUser(signUpUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = userService.userLogin(loginRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
    }
}
