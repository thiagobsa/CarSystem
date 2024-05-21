package com.example.carsystem.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	
	
	
    private Long id;

    private String firstName;
    
    private String lastName;
    
    private String birthday;
    
    private String phone;
    
    private String email;
    
    private String login;
    
    private String password;
    
    private String createdAt;

    private String lastLogin;
    

    
    public UserResponse(Long id, String firstName, String lastName, String birthday, String phone, String email, String createdAt, String lastLogin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
    }

}
