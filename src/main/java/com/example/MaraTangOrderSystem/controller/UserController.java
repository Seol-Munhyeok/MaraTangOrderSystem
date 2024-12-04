package com.example.MaraTangOrderSystem.controller;

import com.example.MaraTangOrderSystem.dto.Login.LoginRequestDto;
import com.example.MaraTangOrderSystem.dto.Login.LoginResponseDto;
import com.example.MaraTangOrderSystem.dto.User.SignUpUserDto;
import com.example.MaraTangOrderSystem.dto.User.UserDto;
import com.example.MaraTangOrderSystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value = "/signup", consumes = "multipart/form-data")
    public ResponseEntity<UserDto> signUpUser(
            @RequestPart("user") SignUpUserDto signUpUserDto,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        UserDto userDto = userService.signUpUser(signUpUserDto, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(
            @RequestBody LoginRequestDto loginRequestDto,
            HttpSession session) {
        LoginResponseDto loginResponseDto = userService.userLogin(loginRequestDto);
        session.setAttribute("userId", loginResponseDto.userId());
        return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpSession session) {
        session.invalidate();
        return ResponseEntity.status(HttpStatus.OK).body("로그아웃 했습니다.");
    }
}