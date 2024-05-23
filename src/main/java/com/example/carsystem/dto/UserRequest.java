package com.example.carsystem.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
public class UserRequest {
	
	@NotBlank(message = "First name cannot be blank")
    private String firstName;
    
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;
    
    @NotBlank(message = "Email cannot be blank")
    private String email;
    
    @NotBlank(message = "Birthday cannot be blank")
    private String birthday;
    
    @NotBlank(message = "Login cannot be blank")
    private String login;
    
    @NotBlank(message = "Password cannot be blank")
    private String password;
    
    @NotBlank(message = "Phone cannot be blank")
    private String phone;
    
    @Valid
    private List<CarRequest> cars = new ArrayList<>();
}