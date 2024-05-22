package com.example.carsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.carsystem.dto.SigninRequest;
import com.example.carsystem.dto.SigninResponse;
import com.example.carsystem.dto.UserRequest;
import com.example.carsystem.dto.UserResponse;
import com.example.carsystem.dto.UserResponseCreate;
import com.example.carsystem.service.UserService;

@RestController
public class UserControllerImpl implements UserController{

    @Autowired
    private UserService userService;
    
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public ResponseEntity<UserResponseCreate> create(UserRequest userRequest) {
        
    	return  ResponseEntity.ok(userService.create(userRequest));
    }

    @Override
    public ResponseEntity<Page<UserResponse>> findAllPaged(Pageable pageable) {
        
    	return ResponseEntity.ok(userService.findAllPaged(pageable));
    }

    @Override
    public ResponseEntity<UserResponse> findById(Long id) {
        
    	return  ResponseEntity.ok(userService.findById(id));
    }

    @Override
    public ResponseEntity<UserResponse> update(Long id, UserRequest userRequest) {
        
    	return  ResponseEntity.ok(userService.update(id, userRequest));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        
    	userService.delete(id);
        
    	return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteAll() {
        
    	userService.deleteAll();
        
    	return ResponseEntity.noContent().build();
    }
    
    @Override
    public ResponseEntity<UserResponse> findAuthenticateUser(String token) {
        
    	return  ResponseEntity.ok(userService.findAuthenticateUser(token));
    }
    
    @Override
    public ResponseEntity<SigninResponse> authenticateUser(SigninRequest signinRequest){
        
    	return ResponseEntity.ok(userService.authenticateUser(signinRequest));
    }
}
