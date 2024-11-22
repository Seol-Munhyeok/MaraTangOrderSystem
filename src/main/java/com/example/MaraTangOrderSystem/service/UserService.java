package com.example.MaraTangOrderSystem.service;

import com.example.MaraTangOrderSystem.Converter.DtoConverter;
import com.example.MaraTangOrderSystem.dto.UserDto;
import com.example.MaraTangOrderSystem.model.User;
import com.example.MaraTangOrderSystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto saveUser(UserDto userDto) {
        User user = userRepository.findById(userDto.userId()).orElseThrow();
        userRepository.save(user);
        return DtoConverter.convertToUserDto(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

}
