package com.example.carsystem.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
public class SigninResponse {

    private Long id;

    private String firstName;

    private String lastName;
    
    private String email;
    
    private String birthday;
    
    private String phone;
    
    private String createdAt;
    
    private String lastLogin;
    
    private String accessToken;

    public SigninResponse(){
    }

    public SigninResponse(Long id, String firstName, String lastName, String email, String birthday, String phone, String lastLogin, String createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.phone = phone;
        this.lastLogin = lastLogin;
        this.createdAt = createdAt;
    }

}

