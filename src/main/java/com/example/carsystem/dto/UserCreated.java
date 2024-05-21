package com.example.carsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreated {

    private Long id;
    private String firstName;
    private String lastName;
    private String birthday;
    private String phone;
    private String email;
    private String createdAt;
    private String lastLogin;
    private List<CarResponse> cars = new ArrayList<>();
    
    public UserCreated(Long id, String firstName, String lastName, String birthday, String phone, String email, String createdAt, String lastLogin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
    }
    
    public List<CarResponse> getCars() {
        return cars;
    }

}
