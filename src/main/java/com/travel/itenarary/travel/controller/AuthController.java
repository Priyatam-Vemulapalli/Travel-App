package com.travel.itenarary.travel.controller;


import com.travel.itenarary.travel.dto.*;
import com.travel.itenarary.travel.model.entity.User;
import com.travel.itenarary.travel.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    Logger logger = Logger.getLogger(String.valueOf(AuthController.class));

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<AuthResponse>> signup(@Valid @RequestBody SignupRequest signupRequest) {
        try {
            AuthResponse authResponse = userService.signUp(signupRequest);
            return ResponseEntity.ok(ApiResponse.success("User Registered Successfully", authResponse));
        } catch (RuntimeException e) {
            logger.log(Level.INFO, "Error while Signing up " + e.getMessage());
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            AuthResponse authResponse = userService.login(loginRequest);
            return ResponseEntity.ok(ApiResponse.success("User logged in successfully", authResponse));
        } catch (Exception e) {
            logger.log(Level.FINE, "Error while login " + e.getMessage());
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));

        }
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<User>> getProfile(@Valid @RequestBody UserProfileRequest userProfileRequest) {
        try {
            User user = userService.getUserByUserName(userProfileRequest);
            return ResponseEntity.ok(ApiResponse.success("Profile fetched successfully", user));
        } catch (Exception e) {
            logger.log(Level.INFO, "Error while getting the profile " + e.getMessage());
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));

        }
    }

    @GetMapping("/check-username/{username}")
    public ResponseEntity<ApiResponse<Boolean>> checkUserNameAvailability(@PathVariable String username) {
        try {
            Boolean isAvailable = userService.existByUserName(username);
            return ResponseEntity.ok(ApiResponse.success("Username availability response", isAvailable));

        } catch (Exception e) {
            logger.log(Level.INFO, "Error while checking for the username available " + e.getMessage());
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));

        }
    }

    @GetMapping("/check-email/{email}")
    public ResponseEntity<ApiResponse<Boolean>> checkExistsByEmail(@PathVariable String email) {
        try {
            Boolean isAvailable = userService.existByEmail(email);
            return ResponseEntity.ok(ApiResponse.success("Email availability", isAvailable));
        } catch (Exception e) {
            logger.log(Level.INFO, "Error while checking for email exists " + e.getMessage());
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));

        }
    }
}
