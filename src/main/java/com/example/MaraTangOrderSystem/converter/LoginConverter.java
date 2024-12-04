package com.example.MaraTangOrderSystem.converter;

import com.example.MaraTangOrderSystem.dto.Login.LoginResponseDto;
import com.example.MaraTangOrderSystem.model.User;

public class LoginConverter {
    public static LoginResponseDto convertToLoginResponseDto(User user, String message) {
        return new LoginResponseDto(
                user.getId(),
                user.getName(),
                user.getNickname(),
                user.getEmail(),
                message
        );
    }
}
