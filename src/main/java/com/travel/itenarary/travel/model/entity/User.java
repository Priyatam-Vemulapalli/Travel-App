package com.travel.itenarary.travel.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private boolean enabled;
    private String role;

    @PrePersist // automatically be called before adding into the DB
    protected void onCreate(){
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
    }

    @PreUpdate //automatically called when ever the user is updated
    protected void onUpdate()
    {
        updatedAt = LocalDate.now();
    }

}
