package com.example.carsystem.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.carsystem.dto.SigninRequest;
import com.example.carsystem.dto.SigninResponse;
import com.example.carsystem.dto.UserRequest;
import com.example.carsystem.dto.UserResponse;
import com.example.carsystem.dto.UserResponseCreate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "users")
public interface UserController {
	

    
    @Operation(summary = "Cadastro de usuarios", method = "POST")
    @PostMapping("/api/users")
    ResponseEntity<UserResponseCreate> create(@RequestBody UserRequest userRequest);
    
    @Operation(summary = "Consulta todos os usuários", method = "GET")
    @GetMapping("/api/users")
    ResponseEntity<Page<UserResponse>> findAllPaged(Pageable pageable);

    @Operation(summary = "Consulta usuario por id", method = "GET")
    @GetMapping(value = "/api/users/{id}")
    ResponseEntity<UserResponse> findById(@PathVariable Long id);

    @Operation(summary = "Alterar usuário", method = "PUT")
    @PutMapping(value = "/api/users/{id}")
    ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserRequest userRequest);
    
    @Operation(summary = "Remove usuario por id", method = "DELETE")
    @DeleteMapping(value = "/api/users/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
    
    @Operation(summary = "Deleta todos usuarios tabela de users", method = "DELETE")
    @DeleteMapping(value = "/api/users/deleteAll")
    ResponseEntity<Void> deleteAll();

    @Operation(summary = "Login de usuário", method = "POST")
    @PostMapping(value = "/api/signin")
    ResponseEntity<SigninResponse> authenticateUser(@RequestBody SigninRequest signinRequest);
    
    @Operation(summary = "Recupera informações autenticação do usuário logado", method = "GET")
    @GetMapping(value = "/api/me")
    ResponseEntity<UserResponse> findAuthenticateUser(@RequestHeader("Authorization") String token);
    



}

