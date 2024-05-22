package com.example.carsystem.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
public class UserRequest {
	
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String birthday;
    
    private String login;
    
    private String password;
    
    private String phone;
    
    private List<CarRequest> cars = new ArrayList<>();
}