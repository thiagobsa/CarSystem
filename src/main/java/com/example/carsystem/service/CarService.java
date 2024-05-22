package com.example.carsystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.carsystem.dto.CarRequest;
import com.example.carsystem.dto.CarResponse;

public interface CarService {

	CarResponse create(String token, CarRequest carRequest);
	
    Page<CarResponse> findAllPaged(String token, Pageable pageable);

    CarResponse findById(String token, Long id);

    CarResponse update(String token, Long id, CarRequest carRequest);

    void delete(String token, Long id);
    
    void deleteAll();

    boolean existsByLicensePlate(String licensePlate);

    void validateAtributtes(CarRequest carRequest);


}


