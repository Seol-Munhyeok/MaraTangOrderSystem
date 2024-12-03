package com.example.MaraTangOrderSystem.service;

import com.example.MaraTangOrderSystem.Converter.LoginConverter;
import com.example.MaraTangOrderSystem.Converter.UserConverter;
import com.example.MaraTangOrderSystem.dto.Login.LoginRequestDto;
import com.example.MaraTangOrderSystem.dto.Login.LoginResponseDto;
import com.example.MaraTangOrderSystem.dto.User.SignUpUserDto;
import com.example.MaraTangOrderSystem.dto.User.UserDto;
import com.example.MaraTangOrderSystem.model.User;
import com.example.MaraTangOrderSystem.repository.UserRepository;
import exception.EmailAlreadyExistsException;
import exception.EmailNotFoundException;
import exception.InvalidPasswordException;
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
        return UserConverter.convertToUserDto(user);
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return UserConverter.convertToUserDto(user);
    }

    public UserDto signUpUser(SignUpUserDto signUpUserDto) {
        validateEmailDuplicate(signUpUserDto.email());

        String hashedPassword = passwordEncoder.encode(signUpUserDto.password());
        User newUser = UserConverter.convertToUserDto(signUpUserDto, hashedPassword);
        userRepository.save(newUser);

        return UserConverter.convertToUserDto(newUser);
    }

    private void validateEmailDuplicate(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException("이미 존재하는 이메일입니다.");
        }
    }

    public LoginResponseDto userLogin(LoginRequestDto loginRequestDto) {
        User user = getUserIfEmailExists(loginRequestDto.email());
        validatePassword(loginRequestDto.password(), user.getPassword());

        return LoginConverter.convertToLoginResponseDto(user, "로그인에 성공했습니다.");
    }

    private User getUserIfEmailExists(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new EmailNotFoundException("이메일이 존재하지 않습니다."));
    }

    private void validatePassword(String inputPassword, String expectedPassword) {
        if (!passwordEncoder.matches(inputPassword, expectedPassword)) {
            throw new InvalidPasswordException("비밀번호가 틀렸습니다.");
        }
    }
}
