package com.travel.itenarary.travel.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public static class UserBuilder{
        private UUID id;
        private String username;
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private LocalDate createdAt;
        private LocalDate updatedAt;
        private boolean enabled;
        private String role;


        public UserBuilder id(UUID id) { this.id = id; return this; }
        public UserBuilder username(String username) { this.username = username; return this; }
        public UserBuilder email(String email) { this.email = email; return this; }
        public UserBuilder password(String password) { this.password = password; return this; }
        public UserBuilder firstName(String firstName) { this.firstName = firstName; return this; }
        public UserBuilder lastName(String lastName) { this.lastName = lastName; return this; }
        public UserBuilder phoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public UserBuilder createdAt(LocalDate createdAt) { this.createdAt = createdAt; return this; }
        public UserBuilder updatedAt(LocalDate updatedAt) { this.updatedAt = updatedAt; return this; }
        public UserBuilder enabled(boolean enabled) { this.enabled = enabled; return this; }
        public UserBuilder role(String role) { this.role = role; return this; }

        public User build(){
            User user = new User();
            user.id= this.id;
            user.userName = this.username;
            user.email = this.password;
            user.firstName= this.firstName;
            user.lastName= this.lastName;
            user.phoneNumber= this.phoneNumber;
            user.createdAt= this.createdAt;
            user.updatedAt = this.updatedAt;
            user.enabled= this.enabled;
            user.role= this.role;

            return user;
        }
    }
}
