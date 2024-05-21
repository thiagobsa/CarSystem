package com.example.carsystem.service;

import com.example.carsystem.dto.SigninRequest;
import com.example.carsystem.dto.SigninResponse;
import com.example.carsystem.dto.UserRequest;
import com.example.carsystem.dto.UserResponse;
import com.example.carsystem.model.UserEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface UserService {

    SigninResponse authenticateUser(SigninRequest signinRequest);

    Page<Object> findAllPaged(Pageable pageable);

    UserResponse create(UserRequest userRequest);

    UserResponse findById(Long id);
    
    UserEntity findUserById(Long id);

    UserResponse update(Long id, UserRequest userRequest);
    
    void validateAttributes(UserRequest userRequest);

    UserResponse findAuthenticateUser(String token);
    
    void delete(Long id);

    void deleteAll();
}
