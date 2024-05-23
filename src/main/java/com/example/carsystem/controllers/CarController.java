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

import com.example.carsystem.dto.CarRequest;
import com.example.carsystem.dto.CarResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "cars")
public interface CarController {
	
    
    @Operation(summary = "Registra carro do usuário", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @PostMapping(value = "/api/cars")
    ResponseEntity<CarResponse> create(@RequestHeader("Authorization") String token,
                                       @Valid @RequestBody CarRequest carRequest);

    @Operation(summary = "Consulta carros do usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cars fetched successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @GetMapping(value = "/api/cars")
    ResponseEntity<Page<CarResponse>> findAllPaged(@RequestHeader("Authorization") String token,
                                                   Pageable pageable);

    @Operation(summary = "Consulta um carro do usuário por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car fetched successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Car not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @GetMapping(value = "/api/cars/{id}")
    ResponseEntity<CarResponse> findById(@RequestHeader("Authorization") String token,
                                                   @PathVariable Long id);

    @Operation(summary = "Modifica um carro do usuário", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Car not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @PutMapping(value = "/api/cars/{id}")
    ResponseEntity<CarResponse> update(@RequestHeader("Authorization") String token,
                                       @PathVariable Long id,
                                       @Valid @RequestBody CarRequest carRequest);
    
    @Operation(summary = "Remove um carro do usuário por id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Car deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Car not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @DeleteMapping(value = "/api/cars/{id}")
    ResponseEntity<Void> delete(@RequestHeader("Authorization") String token,
                                         @PathVariable Long id);

    @Operation(summary = "Deleta todos carros tabela de cars", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "All cars deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @DeleteMapping(value = "/api/cars/deleteAll")
    ResponseEntity<Void> deleteAll();
}
