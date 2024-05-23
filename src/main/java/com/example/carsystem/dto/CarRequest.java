package com.example.carsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Year cannot be null")
    @Min(value = 1886, message = "Year should not be less than 1886")
    private Integer year;

    @NotBlank(message = "License plate cannot be blank")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "License plate should contain only uppercase letters, numbers, and hyphens")
    @Size(max = 10, message = "License plate should not exceed 10 characters")
    private String licensePlate;

    @NotBlank(message = "Model cannot be blank")
    @Size(max = 50, message = "Model should not exceed 50 characters")
    private String model;

    @NotBlank(message = "Color cannot be blank")
    @Size(max = 30, message = "Color should not exceed 30 characters")
    private String color;
}