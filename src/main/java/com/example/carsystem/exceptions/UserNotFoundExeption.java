package com.example.carsystem.exceptions;

public class UserNotFoundExeption extends RuntimeException {
	
	public UserNotFoundExeption(String msg) {
        super(msg);
    }
}

