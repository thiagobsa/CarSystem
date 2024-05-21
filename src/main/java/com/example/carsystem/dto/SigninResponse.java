package com.example.carsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SigninResponse {

    private Long id;
    
    private String firstName;
    
    private String lastName;
    
    private String birthday;
    
    private String phone;
    
    private String email;
    
    private String createdAt;
    
    private String lastLogin;
    
    private String accessToken;
    
}