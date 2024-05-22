package com.example.carsystem.exceptions;

public class LoginPasswordException extends RuntimeException {
    public LoginPasswordException(String msg) {
        super(msg);
    }
}