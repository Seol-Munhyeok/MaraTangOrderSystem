package com.example.MaraTangOrderSystem.Converter;

import com.example.MaraTangOrderSystem.dto.User.SignUpUserDto;
import com.example.MaraTangOrderSystem.dto.User.UserDto;
import com.example.MaraTangOrderSystem.model.User;

public class UserConverter {
    public static User convertToUserDto(SignUpUserDto signUpUserDto, String hashedPassword) {
        return User.create(
                signUpUserDto.email(),
                hashedPassword,
                signUpUserDto.nickname(),
                signUpUserDto.name()
        );
    }

    public static UserDto convertToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                null,
                user.getNickname(),
                user.getName(),
                user.getPhoneNumber(),
                user.getOrders()
        );
    }
}
