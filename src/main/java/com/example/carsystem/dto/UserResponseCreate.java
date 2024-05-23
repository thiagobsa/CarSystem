package com.example.carsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
public class UserResponseCreate {


    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String birthday;

    private String phone;
    
    private String createdAt;
    
    private String lastLogin;

    private List<CarResponse> cars = new ArrayList<>();

    public UserResponseCreate(){
    }

    public UserResponseCreate(Long id, String firstName, String lastName, String email, String birthday, String phone, String createdAt, String lastLogin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.phone = phone;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
    }

    public List<CarResponse> getCars() {
        return cars;
    }

	public void setUsername(String string) {
		this.setUsername(string);
		
	}
}
