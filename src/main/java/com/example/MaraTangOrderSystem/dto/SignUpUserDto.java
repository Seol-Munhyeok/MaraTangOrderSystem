package com.example.MaraTangOrderSystem.dto;

public record SignUpUserDto(
        String email,
        String password,
        String nickname
) {}