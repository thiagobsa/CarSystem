package com.example.carsystem.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.carsystem.dto.CarRequest;
import com.example.carsystem.dto.CarResponse;
import com.example.carsystem.service.CarService;

public class CarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarControllerImpl carController;

    private CarResponse testCarResponse;
    
    private CarRequest testCarRequest;

    @BeforeEach
    public void setUp() {
        
        testCarResponse = new CarResponse();
        testCarResponse.setId(1L);
        testCarResponse.setModel("TestModel");
    }

    @Test
    public void testCreateCar_Success() {
        when(carService.create("token", testCarRequest));

        CarRequest request = new CarRequest();
        ResponseEntity<CarResponse> response = carController.create("token", request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testCarResponse, response.getBody());
    }

    @Test
    public void testFindAllPaged_Success() {
        
        Page<CarResponse> page = mock(Page.class);
        when(carService.findAllPaged(any(), any())).thenReturn(page);

        Pageable pageable = mock(Pageable.class);
        ResponseEntity<Page<CarResponse>> response = carController.findAllPaged("token", pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(page, response.getBody());
    }

    @Test
    public void testFindById_Success() {
        
        when(carService.findById("token",(long) 1));

        ResponseEntity<CarResponse> response = carController.findById("token", 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testCarResponse, response.getBody());
    }

    @Test
    public void testFindById_CarNotFound() {
        when(carService.findById("token",(long) 1)).thenReturn(null);

        ResponseEntity<CarResponse> response = carController.findById("token", 1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdateCar_Success() {
        
        CarResponse updatedCarResponse = mock(CarResponse.class);
        when(carService.update("token",any(), any())).thenReturn(updatedCarResponse);

        CarRequest request = new CarRequest();
        ResponseEntity<CarResponse> response = carController.update("token", 1L, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCarResponse, response.getBody());
    }

    @Test
    public void testUpdateCar_CarNotFound() {
        when(carService.update("token",any(), any())).thenReturn(null);

        CarRequest request = new CarRequest();
        ResponseEntity<CarResponse> response = carController.update("token", 1L, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteCar_Success() {
        ResponseEntity<Void> response = carController.delete("token", 1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(carService).delete("token",any());
    }

    @Test
    public void testDeleteAllCars_Success() {
        ResponseEntity<Void> response = carController.deleteAll();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(carService).deleteAll();
    }

}
