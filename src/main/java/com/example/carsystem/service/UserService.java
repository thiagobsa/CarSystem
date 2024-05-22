package com.example.carsystem.service;

import com.example.carsystem.dto.SigninRequest;
import com.example.carsystem.dto.SigninResponse;
import com.example.carsystem.dto.UserRequest;
import com.example.carsystem.dto.UserResponse;
import com.example.carsystem.model.UserEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface UserService {

	Page<UserResponse> findAllPaged(Pageable pageable);
    UserResponse findById(Long id);
    void delete(Long id);
    UserResponse update(Long id, UserRequest userRequest);
    UserResponse create(String token, UserRequest userRequest);
    void deleteAll();
    UserResponse findAuthenticateUser(String token);
}
