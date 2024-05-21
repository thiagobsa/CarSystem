package com.example.carsystem.map;

import org.springframework.stereotype.Component;

import com.example.carsystem.dto.CarResponse;
import com.example.carsystem.model.CarEntity;

@Component
public class CarMapper implements Mapper<CarEntity, CarResponse> {

    @Override
    public CarResponse map(CarEntity input) {
        return new CarResponse(
        		
        		
                input.getId(),
                input.getModel(),
                input.getColor(),
                input.getLicensePlate(),
                input.getCarYear()
        );
    }
}