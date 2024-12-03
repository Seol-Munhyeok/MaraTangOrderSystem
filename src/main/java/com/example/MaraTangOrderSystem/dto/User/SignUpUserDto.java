package com.example.MaraTangOrderSystem.dto.User;

public record SignUpUserDto(
        String email,
        String password,
        String nickname,
        String name
) {}