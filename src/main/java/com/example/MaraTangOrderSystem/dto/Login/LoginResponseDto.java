package com.example.MaraTangOrderSystem.dto.Login;

public record LoginResponseDto (
        Long userId,
        String name,
        String nickname,
        String email,
        String message
) {}
