package com.travel.itenarary.travel.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequest {
    @NotBlank(message = "username is required")
    private String userName;
    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
