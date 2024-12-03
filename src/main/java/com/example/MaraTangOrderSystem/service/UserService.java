package com.example.MaraTangOrderSystem.service;

import com.example.MaraTangOrderSystem.Converter.DtoConverter;
import com.example.MaraTangOrderSystem.dto.LoginRequestDto;
import com.example.MaraTangOrderSystem.dto.LoginResponseDto;
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

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return DtoConverter.convertToUserDto(user);
    }

    public UserDto signUpUser(SignUpUserDto signUpUserDto) {
        validateEmailDuplicate(signUpUserDto.email());

        String hashedPassword = passwordEncoder.encode(signUpUserDto.password());
        User newUser = DtoConverter.convertToUserDto(signUpUserDto, hashedPassword);
        userRepository.save(newUser);

        return DtoConverter.convertToUserDto(newUser);
    }

    private void validateEmailDuplicate(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
    }

    public LoginResponseDto userLogin(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.email()).orElseThrow();
        if (!passwordEncoder.matches(loginRequestDto.password(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
        }

        return new LoginResponseDto(
                user.getId(),
                user.getName(),
                user.getNickname(),
                user.getEmail(),
                "로그인에 성공했습니다."
        );
    }
}
