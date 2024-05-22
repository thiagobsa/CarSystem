package com.example.carsystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.carsystem.dto.CarRequest;
import com.example.carsystem.dto.CarResponse;
import com.example.carsystem.model.CarEntity;

public interface CarService {

	Page<CarResponse> findAllPaged(String token, Pageable pageable);
    CarResponse findById(String token, Long id);
    void delete(String token, Long id);
    CarResponse update(String token, Long id, CarRequest carRequest);
    CarResponse create(String token, CarRequest carRequest);
    boolean existsByLicensePlate(String licensePlate);
    void validateAttributes(CarRequest carRequest);
    void deleteAll();
}

