package com.travel.itenarary.travel.dto;


import lombok.*;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String token;
    private String type = "Bearer";
    private UUID id;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
}
