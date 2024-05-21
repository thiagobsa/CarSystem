package com.example.carsystem.dto;

import java.time.Instant;
import java.util.Date;

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
    
    private Date birthday;
    
    private String phone;
    
    private String email;
    
    private String login;
    
    private String password;
    
    private Instant createdAt;

    private Instant lastLogin;

}
