package com.example.MaraTangOrderSystem.service;

import com.example.MaraTangOrderSystem.Converter.DtoConverter;
import com.example.MaraTangOrderSystem.dto.SignUpUserDto;
import com.example.MaraTangOrderSystem.dto.UserDto;
import com.example.MaraTangOrderSystem.model.User;
import com.example.MaraTangOrderSystem.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    UserRepository userRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto saveUser(UserDto userDto) {
        User user = userRepository.findById(userDto.userId()).orElseThrow();
        userRepository.save(user);
        return DtoConverter.convertToUserDto(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public UserDto signUpUser(SignUpUserDto signUpUserDto) {
        validateEmailDuplicate(signUpUserDto.email());

        String hashedPassword = passwordEncoder.encode(signUpUserDto.password());
        User newUser = User.create(
                signUpUserDto.email(),
                hashedPassword,
                signUpUserDto.nickname()
        );

        userRepository.save(newUser);

        return DtoConverter.convertToUserDto(newUser);
    }

    private void validateEmailDuplicate(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
    }


}
