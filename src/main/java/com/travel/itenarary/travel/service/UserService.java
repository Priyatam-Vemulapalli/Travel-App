package com.travel.itenarary.travel.service;

import com.travel.itenarary.travel.dto.AuthResponse;
import com.travel.itenarary.travel.dto.LoginRequest;
import com.travel.itenarary.travel.dto.SignupRequest;
import com.travel.itenarary.travel.dto.UserProfileRequest;
import com.travel.itenarary.travel.model.entity.User;
import com.travel.itenarary.travel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;

    public AuthResponse signUp(SignupRequest signupRequest) {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = User.builder().userName(signupRequest.getUsername())
                .email(signupRequest.getEmail())
                .password(signupRequest.getPassword())
                .firstName(signupRequest.getFirstName())
                .lastName(signupRequest.getLastName())
                .phoneNumber(signupRequest.getPhoneNumber())
                .build();

        User savedUser = userRepository.save(user);
        String token = "";
        return new AuthResponse(
                token,
                "Bearer",
                savedUser.getId(),
                savedUser.getUserName(),
                savedUser.getEmail(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getRole()
        );
    }

    public AuthResponse login(LoginRequest loginRequest) throws BadRequestException {

        User user = userRepository.findByUsername(loginRequest.getUserName()).orElse(null);
        if (user == null) {
            throw new BadRequestException("User with username does not exist");
        }
        String token = "";
        return new AuthResponse(
                token,
                "Bearer",
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole()
        );

    }

    public User getUserByUserName(UserProfileRequest userProfileRequest) {
        return userRepository.findByUsername(userProfileRequest.getUserName()).orElse(null);
    }

    public Boolean existByUserName(String username) {
        return userRepository.existsByUsername(username);
    }

    public Boolean existByEmail(String email) {

        return userRepository.existsByEmail(email);
    }
}
