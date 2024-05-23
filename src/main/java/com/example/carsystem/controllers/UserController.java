package com.example.carsystem.controllers;

import javax.validation.Valid;

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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "users")
public interface UserController {
	
    @Operation(summary = "Cadastro de usuarios", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @PostMapping("/api/users")
    ResponseEntity<UserResponseCreate> create(@Valid @RequestBody UserRequest userRequest);
    
    @Operation(summary = "Consulta todos os usuários", method = "GET")
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "200", description = "Users fetched successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @GetMapping("/api/users")
    ResponseEntity<Page<UserResponse>> findAllPaged(Pageable pageable);

    @Operation(summary = "Consulta usuario por id", method = "GET")
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "200", description = "User fetched successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @GetMapping(value = "/api/users/{id}")
    ResponseEntity<UserResponse> findById(@PathVariable Long id);

    @Operation(summary = "Alterar usuário", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @PutMapping(value = "/api/users/{id}")
    ResponseEntity<UserResponse> update(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest);
    
    @Operation(summary = "Remove usuario por id", method = "DELETE")
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @DeleteMapping(value = "/api/users/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
    
    @Operation(summary = "Deleta todos usuarios tabela de users", method = "DELETE")
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "204", description = "All users deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @DeleteMapping(value = "/api/users/deleteAll")
    ResponseEntity<Void> deleteAll();

    @Operation(summary = "Login de usuário", method = "POST")
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "200", description = "User authenticated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @PostMapping(value = "/api/signin")
    ResponseEntity<SigninResponse> authenticateUser(@Valid @RequestBody SigninRequest signinRequest);
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "200", description = "Authentication information retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @Operation(summary = "Recupera informações autenticação do usuário logado", method = "GET")
    @GetMapping(value = "/api/me")
    ResponseEntity<UserResponse> findAuthenticateUser(@RequestHeader("Authorization") String token);
}
