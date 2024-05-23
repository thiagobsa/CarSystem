package com.example.carsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarResponse {
    
    
    private Long id;
    
    private Integer year;
    
    private String licensePlate;
    
    private String model;
    
    private String color;
}