package com.example.MaraTangOrderSystem.dto;

public record LoginResponseDto (
        Long userId,
        String name,
        String nickname,
        String email,
        String message
) {}
