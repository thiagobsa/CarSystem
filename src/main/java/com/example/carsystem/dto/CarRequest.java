package com.example.carsystem.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRequest {
	
	@NotBlank
    private String model;
    
	@NotBlank
    private String color;

	@NotBlank
    private String licensePlate;

    private Integer year;

}
