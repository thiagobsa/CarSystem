package com.example.carsystem.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.example.carsystem.model.CarEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

	@NotBlank
    private String firstName;
	
	@NotBlank
    private String lastName;
	
	@NotBlank
    private Date birthday;
    
    private String phone;
    
    @Email
    @NotBlank
    private String email;
    
    @NotBlank
    private String login;
    
    @NotBlank
    private String password;
    
    private List<CarEntity> cars = new ArrayList<>();

}
