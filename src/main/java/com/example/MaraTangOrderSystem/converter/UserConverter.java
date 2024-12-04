package com.example.MaraTangOrderSystem.converter;

import com.example.MaraTangOrderSystem.dto.User.SignUpUserDto;
import com.example.MaraTangOrderSystem.dto.User.UserDto;
import com.example.MaraTangOrderSystem.model.User;

public class UserConverter {
    public static User convertToUserDto(SignUpUserDto signUpUserDto, String hashedPassword, String profileImagePath) {
        return User.create(
                signUpUserDto.email(),
                hashedPassword,
                signUpUserDto.nickname(),
                signUpUserDto.name(),
                profileImagePath
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
                user.getOrders(),
                user.getProfileImagePath()
        );
    }
}
