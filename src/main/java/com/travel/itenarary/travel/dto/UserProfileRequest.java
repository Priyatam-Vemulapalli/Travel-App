package com.travel.itenarary.travel.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserProfileRequest {
    public String UserName;
    public String PhoneNumber;
}
