package com.example.carsystem.map;

import org.springframework.stereotype.Component;

import com.example.carsystem.dto.CarResponse;
import com.example.carsystem.models.CarEntity;

@Component
public class CarResponseMapper implements Mapper<CarEntity, CarResponse> {

    @Override
    public CarResponse map(CarEntity input) {

        return new CarResponse(input.getId(),
                input.getYear(),
                input.getLicensePlate(),
                input.getModel(),
                input.getColor());
    }
}