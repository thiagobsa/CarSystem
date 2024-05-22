package com.example.carsystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.carsystem.dto.SigninRequest;
import com.example.carsystem.dto.SigninResponse;
import com.example.carsystem.dto.UserRequest;
import com.example.carsystem.dto.UserResponse;
import com.example.carsystem.dto.UserResponseCreate;

public interface UserService {
	
	UserResponseCreate create(UserRequest userRequest);

    Page<UserResponse> findAllPaged(Pageable pageable);

    UserResponse findById(Long id);
    
    UserResponse update(Long id, UserRequest userRequest);

    void delete(Long id);

    void deleteAll();

    UserResponse findAuthenticateUser(String token);

    void validateAtributtes(UserRequest userRequest);
    
    SigninResponse authenticateUser(SigninRequest signinRequest);
    
    
}

