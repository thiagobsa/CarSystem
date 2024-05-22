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

import com.example.carsystem.dto.CarRequest;
import com.example.carsystem.dto.CarResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "cars")
public interface CarController {
	
    @Operation(summary = "Registra carro do usuário", method = "POST")
    @PostMapping(value = "/api/cars")
    ResponseEntity<CarResponse> create(@RequestHeader("Authorization") String token,
                                       @RequestBody CarRequest carRequest);

    @Operation(summary = "Consulta carros do usuário", method = "GET")
    @GetMapping(value = "/api/cars")
    ResponseEntity<Page<CarResponse>> findAllPaged(@RequestHeader("Authorization") String token,
                                                   Pageable pageable);

    @Operation(summary = "Consulta um carro do usuário por id", method = "GET")
    @GetMapping(value = "/api/cars/{id}")
    ResponseEntity<CarResponse> findById(@RequestHeader("Authorization") String token,
                                                   @PathVariable Long id);

    @Operation(summary = "Modifica um carro do usuário", method = "PUT")
    @PutMapping(value = "/api/cars/{id}")
    ResponseEntity<CarResponse> update(@RequestHeader("Authorization") String token,
                                       @PathVariable Long id,
                                       @RequestBody CarRequest carRequest);
    
    @Operation(summary = "Remove um carro do usuário por id", method = "DELETE")
    @DeleteMapping(value = "/api/cars/{id}")
    ResponseEntity<Void> delete(@RequestHeader("Authorization") String token,
                                         @PathVariable Long id);

    @Operation(summary = "Deleta todos carros tabela de cars", method = "DELETE")
    @DeleteMapping(value = "/api/cars/deleteAll")
    ResponseEntity<Void> deleteAll();
}
