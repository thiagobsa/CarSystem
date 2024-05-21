package com.example.carsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {
	
	private Long id;
	
    private String model;
    
    private String color;
	
    private String licensePlate;

    private Integer year;

}
