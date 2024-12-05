package com.example.MaraTangOrderSystem.service;

import com.example.MaraTangOrderSystem.converter.LoginConverter;
import com.example.MaraTangOrderSystem.converter.UserConverter;
import com.example.MaraTangOrderSystem.dto.Login.LoginRequestDto;
import com.example.MaraTangOrderSystem.dto.Login.LoginResponseDto;
import com.example.MaraTangOrderSystem.dto.User.SignUpUserDto;
import com.example.MaraTangOrderSystem.dto.User.UserDto;
import com.example.MaraTangOrderSystem.exception.*;
import com.example.MaraTangOrderSystem.model.User;
import com.example.MaraTangOrderSystem.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

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
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found."));
        return UserConverter.convertToUserDto(user);
    }

    public UserDto signUpUser(SignUpUserDto signUpUserDto, MultipartFile image) {
        validateEmailDuplicate(signUpUserDto.email());

        String hashedPassword = passwordEncoder.encode(signUpUserDto.password());
        String profileImagePath = resolveProfileImagePath(image);

        User newUser = UserConverter.convertToUserDto(signUpUserDto, hashedPassword, profileImagePath);
        userRepository.save(newUser);

        return UserConverter.convertToUserDto(newUser);
    }

    private String resolveProfileImagePath(MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            return saveImage(image);
        }
        return "/images/default-profile.png";
    }

    private String saveImage(MultipartFile image) {
        String basePath = "C:\\MaraTangOrderSystem\\src\\main\\resources\\static\\images\\";
        createDirectoryIfNotExists(basePath);

        String filename = generateUniqueFilename(image);
        File file = new File(basePath, filename);

        transferImageToFile(image, file);

        return "/images/" + filename;
    }

    private void createDirectoryIfNotExists(String basePath) {
        File directory = new File(basePath);
        if (!directory.exists() && !directory.mkdirs()) {
            throw new ImageStorageException("이미지 저장 경로 생성 실패: " + basePath);
        }
    }

    private String generateUniqueFilename(MultipartFile image) {
        return UUID.randomUUID() + "_" + Objects.requireNonNull(image.getOriginalFilename());
    }

    private void transferImageToFile(MultipartFile image, File file) {
        try {
            image.transferTo(file);
        } catch (IOException e) {
            throw new ImageStorageException("이미지 저장 실패: " + file.getName(), e);
        }
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

    public void validateUserSession(Long sessionUserId, Long requestUserId) {
        if (!sessionUserId.equals(requestUserId)) {
            throw new UnauthorizedAccessException("허가되지 않은 접근");
        }
    }
}
